package com.som.library.librarySystem.enums;

public class Types {
    public enum ItemFormat {
        DVD("Dvd"),
        BOOK("Book");

        private String itemFormat;

        ItemFormat(String itemFormat) {
            this.itemFormat = itemFormat;
        }

        public String getItemFormat() {
            return itemFormat;
        }
    }

    public enum ReservationStatus{
        GRANT("Grant"),
        DENY("Deny");
        private String reservationStatus;

        ReservationStatus(String reservationStatus) {
            this.reservationStatus = reservationStatus;
        }

        public String getReservationStatus() {
            return reservationStatus;
        }
    }

    public enum AccountStatus{
        ACTIVE("Active"),
        CLOSED("Closed"),
        BLACKLISTED("BlackListed");
        private String accountStatus;

        AccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
        }

        public String getAccountStatus() {
            return accountStatus;
        }
    }
}
