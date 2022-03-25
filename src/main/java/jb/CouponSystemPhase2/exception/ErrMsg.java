package jb.CouponSystemPhase2.exception;

public enum ErrMsg {
    INVALID_NAME_OR_EMAIL("sorry,you can't update company name or email"),
    Already_Exist("Already Exist, you can't add this"),
    NOT_Exist("Not Exist, you can't use this"),
    UPDATE_NOT_Exist_COUPONID("Coupon id Not Exist, you can't update coupon id"),
    UPDATE_NOT_Exist_COMANYID("you can't update company id"),
    DATE_NOT_VALID("Date not valid anymore"),
    INVALID_EMAIL_OR_PASSWORD("wrong password or email");

    String description;

    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
