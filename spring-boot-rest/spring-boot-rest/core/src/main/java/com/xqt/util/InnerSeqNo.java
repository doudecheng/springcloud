package com.xqt.util;



import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 内部唯一交易号
 *
 * @author Andy
 */
public final class InnerSeqNo {
    private String uniqueNo;

    private InnerSeqNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public String getUniqueNo() {
        return uniqueNo;
    }

    public void setUniqueNo(String uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

	public static InnerSeqNo getInstance() {
		return new InnerSeqNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    }

    @Override
    public String toString() {
        return uniqueNo;
    }

}
