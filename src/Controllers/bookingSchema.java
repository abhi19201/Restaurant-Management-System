package Controllers;


import java.io.Serializable;

public class bookingSchema implements Serializable {

        private int _bookingId;
        private String name;
        private int table_number;
        private String date;
        private String contact;
        private String status;

        public bookingSchema(int _bookingId, String name, int table_number, String date, String contact,String status) {
            this._bookingId = _bookingId;
            this.name = name;
            this.table_number = table_number;
            //this.time = time;
            this.date = date;
            this.contact = contact;
            this.status = status;
        }

        public int getId() {
            return _bookingId;
        }

        public String getName() {
            return name;
        }

        public int getTable_number() {
            return table_number;
        }



        public String getDate() { return date; }

        public String getContact() {
            return contact;
        }

    public String getStatus() { return status; }

}
