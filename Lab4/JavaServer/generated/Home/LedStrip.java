//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `home.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Home;

public interface LedStrip extends Light
{
    void setNumberOfLeds(int numberOfLeds, com.zeroc.Ice.Current current)
        throws InvalidArgumentException;

    int getNumberOfLeds(com.zeroc.Ice.Current current);

    void setLedColor(int ledIndex, RGB color, com.zeroc.Ice.Current current)
        throws DeviceDeactivatedException,
               InvalidArgumentException;

    RGB getLedColor(int ledIndex, com.zeroc.Ice.Current current)
        throws DeviceDeactivatedException,
               InvalidArgumentException;

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Home::ActuatorDevice",
        "::Home::Device",
        "::Home::LedStrip",
        "::Home::Light",
        "::Ice::Object"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::Home::LedStrip";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_setNumberOfLeds(LedStrip obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_numberOfLeds;
        iceP_numberOfLeds = istr.readInt();
        inS.endReadParams();
        obj.setNumberOfLeds(iceP_numberOfLeds, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getNumberOfLeds(LedStrip obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        int ret = obj.getNumberOfLeds(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeInt(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_setLedColor(LedStrip obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_ledIndex;
        RGB iceP_color;
        iceP_ledIndex = istr.readInt();
        iceP_color = RGB.ice_read(istr);
        inS.endReadParams();
        obj.setLedColor(iceP_ledIndex, iceP_color, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getLedColor(LedStrip obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_ledIndex;
        iceP_ledIndex = istr.readInt();
        inS.endReadParams();
        RGB ret = obj.getLedColor(iceP_ledIndex, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        RGB.ice_write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "activate",
        "deactivate",
        "getBrightness",
        "getLedColor",
        "getName",
        "getNumberOfLeds",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "isActive",
        "setBrightness",
        "setLedColor",
        "setName",
        "setNumberOfLeds"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ActuatorDevice._iceD_activate(this, in, current);
            }
            case 1:
            {
                return ActuatorDevice._iceD_deactivate(this, in, current);
            }
            case 2:
            {
                return Light._iceD_getBrightness(this, in, current);
            }
            case 3:
            {
                return _iceD_getLedColor(this, in, current);
            }
            case 4:
            {
                return Device._iceD_getName(this, in, current);
            }
            case 5:
            {
                return _iceD_getNumberOfLeds(this, in, current);
            }
            case 6:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 7:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 8:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 9:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 10:
            {
                return ActuatorDevice._iceD_isActive(this, in, current);
            }
            case 11:
            {
                return Light._iceD_setBrightness(this, in, current);
            }
            case 12:
            {
                return _iceD_setLedColor(this, in, current);
            }
            case 13:
            {
                return Device._iceD_setName(this, in, current);
            }
            case 14:
            {
                return _iceD_setNumberOfLeds(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}