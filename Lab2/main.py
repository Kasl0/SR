#uvicorn main:app --reload
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
import requests

exchange_rates_url = "https://open.er-api.com/v6/latest/"
bitcoin_price_url = "https://api.coindesk.com/v1/bpi/currentprice.json"

def get_all_currency_codes():
    response = requests.get(exchange_rates_url + "EUR").json()
    if response["result"] != "success":
        return []
    return list(response["rates"].keys())

def get_bitcoin_price():
    response = requests.get(bitcoin_price_url).json()
    bitcoin_price = response["bpi"]["USD"]["rate"]
    return float(bitcoin_price.replace(",", ""))

app=FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["GET", "POST", "PUT", "DELETE"],
    allow_headers=["*"],
)

@app.get("/exchange_rate/{currency_code}")
async def get_exchange_rate_info(currency_code: str):
    return requests.get(exchange_rates_url + currency_code).json()

@app.get("/bitcoin_price")
async def get_bitcoin_price_info():
    return requests.get(bitcoin_price_url).json()

@app.get("/myApi/currency_codes")
async def available_currency_codes():
    return {"Available currency codes": get_all_currency_codes()}

@app.get("/myApi/{currency_code}")
async def get_currency_info(currency_code: str):
    response = requests.get(exchange_rates_url + currency_code).json()
    if response["result"] != "success":
        return {"ERROR": "Result = " + response["result"]}
    exchange_rate = response["rates"]["USD"]

    bitcoin_price = get_bitcoin_price()
    return {
        f"Cost of 1 {currency_code} in USD": exchange_rate,
        f"Cost of 1 USD in {currency_code}": 1 / exchange_rate,
        "Cost of 1 BITCOIN in USD": bitcoin_price,
        f"Cost of 1 BITCOIN in {currency_code}":  1 / exchange_rate * bitcoin_price
        }
