public class Main {
    public static void main(String[] args) {
        Apartment[] apartments = new Apartment[3];
        apartments[0] = new Residential("רחוב א", 1, "תל אביב", 1000000, 2);
        apartments[1] = new RoofA("רחוב ב", 2, "תל אביב", 1200000, true);
        apartments[2] = new Residential("רחוב ג", 3, "ירושלים", 800000, 4);

        int count = countApartmentsInTelAvivAboveFloor(apartments);
        System.out.println("מספר הדירות בתל אביב בקומה השלישית ומעלה: " + count);
    }
    public static int countApartmentsInTelAvivAboveFloor(Apartment[] apartments) {
        int count = 0;
        for (Apartment apartment : apartments) {
            if (apartment.getCity().equals("תל אביב") && apartment instanceof Residential) {
                Residential residential = (Residential) apartment;
                if (residential.getFloor() >= 3) {
                    count++;
                }
            }
        }
        return count;
    }

}
