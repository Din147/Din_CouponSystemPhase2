package jb.CouponSystemPhase2.exception;

public class CouponSystemException extends Exception {
    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getDescription());
    }
}

