package usaco.silver.jan2018.rentalservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Long> cows = Arrays.asList(new Long[] {6L, 2L, 4L, 7L, 1L});
        MilkProduceCalculator produceCalculator = new MilkProduceCalculator(cows);

        List<Long> rentals = Arrays.asList(new Long[] {250L, 80L, 100L, 40L});
        RentalCalculator rentalCalculator = new RentalCalculator(rentals);

        List<MilkBuyerRequest> milkBuyerRequests = new ArrayList<>();
        milkBuyerRequests.add(new MilkBuyerRequest(10L, 25L));
        milkBuyerRequests.add(new MilkBuyerRequest(2L, 10L));
        milkBuyerRequests.add(new MilkBuyerRequest(15L, 15L));
        MilkEarningCalculator milkEarningCalculator = new MilkEarningCalculator(milkBuyerRequests);

        long maxMoney = 0;
        for (int i = 0; i < cows.size(); i++) {
            long money = 0;
            long milkProduced = produceCalculator.milkProduced(cows.size() - i);
            money += rentalCalculator.moneyForRental(i);
            money += milkEarningCalculator.moneyForMilk(milkProduced);
            maxMoney = Math.max(money, maxMoney);
        }

        System.out.println(maxMoney);
    }
}


// possible performance enhancement
// to cache the results

class MilkProduceCalculator {
    List<Long> milkProduceList;

    public MilkProduceCalculator(List<Long> cows) {
        Collections.sort(cows, (a, b) -> b.compareTo(a));
        this.milkProduceList = cows;
    }

    public long milkProduced(int number) {
        long totalMilk = 0;
        for (int i = 0; i < number && i < milkProduceList.size(); i++) {
            totalMilk += milkProduceList.get(i);
        }

        return totalMilk;
    }
}


class RentalCalculator {
    List<Long> rentalRequests;

    public RentalCalculator(List<Long> rentalRequests) {
        Collections.sort(rentalRequests, (a, b) -> b.compareTo(a));
        this.rentalRequests = rentalRequests;
    }

    public long moneyForRental(int cows) {
        long totalMoneyInCents = 0;
        for (int i = 0; i < cows && i < rentalRequests.size(); i++) {
            totalMoneyInCents += rentalRequests.get(i);
        }

        return totalMoneyInCents;
    }
}


class MilkEarningCalculator {
    List<MilkBuyerRequest> milkBuyerRequests;

    public MilkEarningCalculator(List<MilkBuyerRequest> milkBuyerRequests) {
        Collections.sort(milkBuyerRequests, (a, b) -> (int)(b.centsPerGallon - a.centsPerGallon));
        this.milkBuyerRequests = milkBuyerRequests;
    }

    public long moneyForMilk(long milkProduced) {
        long totalMoneyInCents = 0;
        for (MilkBuyerRequest req: milkBuyerRequests) {
            if (milkProduced <=0) break;

            long gallonsToBuy = Math.min(milkProduced, req.quantity);
            totalMoneyInCents += gallonsToBuy * req.centsPerGallon;
            milkProduced -= gallonsToBuy;
        }

        return totalMoneyInCents;
    }
}

class MilkBuyerRequest {
    long quantity;
    long centsPerGallon;

    public MilkBuyerRequest(long quantity, long centsPerGallon) {
        this.quantity = quantity;
        this.centsPerGallon = centsPerGallon;
    }
}