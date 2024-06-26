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

public class RGB implements java.lang.Cloneable,
                            java.io.Serializable
{
    public int red;

    public int green;

    public int blue;

    public RGB()
    {
    }

    public RGB(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        RGB r = null;
        if(rhs instanceof RGB)
        {
            r = (RGB)rhs;
        }

        if(r != null)
        {
            if(this.red != r.red)
            {
                return false;
            }
            if(this.green != r.green)
            {
                return false;
            }
            if(this.blue != r.blue)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Home::RGB");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, red);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, green);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, blue);
        return h_;
    }

    public RGB clone()
    {
        RGB c = null;
        try
        {
            c = (RGB)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeInt(this.red);
        ostr.writeInt(this.green);
        ostr.writeInt(this.blue);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.red = istr.readInt();
        this.green = istr.readInt();
        this.blue = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, RGB v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public RGB ice_read(com.zeroc.Ice.InputStream istr)
    {
        RGB v = new RGB();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<RGB> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, RGB v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            ostr.writeSize(12);
            ice_write(ostr, v);
        }
    }

    static public java.util.Optional<RGB> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            return java.util.Optional.of(RGB.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final RGB _nullMarshalValue = new RGB();

    /** @hidden */
    public static final long serialVersionUID = -780302238L;
}
