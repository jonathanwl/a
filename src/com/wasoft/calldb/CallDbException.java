/*
 * �Զ���������ݿ��쳣��
 */
package com.wasoft.calldb;

/**
 * <p>Title: �Զ���������ݿ��쳣��</p>
 * <p>Description: �Զ���������ݿ��쳣��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class CallDbException  extends Exception
{
	private static final long serialVersionUID = 1250186012466514393L;

	public CallDbException()
    {
        super();
    }

    public CallDbException(String message)
    {
        super(message);
    }

    public CallDbException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CallDbException(Throwable cause)
    {
        super(cause);
    }

    public CallDbException(String message, int errCode)
    {
        super(message);
        this.errCode = errCode;
    }

    public CallDbException(String message, Throwable cause, int errCode)
    {
        super(message, cause);
        this.errCode = errCode;
    }

    public CallDbException(Throwable cause, int errCode)
    {
        super(cause);
        this.errCode = errCode;
    }

    public String toString()
    {
        return getClass().getName();
    }

    public int getErrCode()
    {
        return errCode;
    }

    //--------------------------------------------------------------------------
    /**
     * �쳣������
     */
    protected int errCode = 0;
    //--------------------------------------------------------------------------
}
