export class Flight {

    public airline: string;
    public inbound: boolean;
    public scheduled: string;

    public constructor(airline: string, scheduled: string, inbound: boolean) {
        this.airline = airline;
        this.scheduled = scheduled;
        this.inbound = inbound;
    }

}
