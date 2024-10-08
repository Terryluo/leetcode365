/*
860. Lemonade Change
Easy

At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at a time (in the order specified by bills). Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill. You must provide the correct change to each customer so that the net transaction is that the customer pays $5.

Note that you do not have any change in hand at first.

Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide every customer with the correct change, or false otherwise.



Example 1:

Input: bills = [5,5,5,10,20]
Output: true
Explanation:
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.
Example 2:

Input: bills = [5,5,10,10,20]
Output: false
Explanation:
From the first two customers in order, we collect two $5 bills.
For the next two customers in order, we collect a $10 bill and give back a $5 bill.
For the last customer, we can not give the change of $15 back because we only have two $10 bills.
Since not every customer received the correct change, the answer is false.


Constraints:

1 <= bills.length <= 10^5
bills[i] is either 5, 10, or 20.
 */
public class Lemonade_Change_860 {
    public boolean lemonadeChange(int[] bills) {
        // since we only have 5, 10, or 20 bills. we can record the number of 5 or 10 bill.
        // if 5 then 5bills++
        // if 10 then 10bills++, 5bills--
        // if 20 then 10bills--, 5bills--
        // return false once we out of 5 or 10 bill
        int fives = 0;
        int tens = 0;
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    fives++;
                    continue;
                case 10:
                    if (fives == 0) {
                        return false;
                    }
                    fives--;
                    tens++;
                    continue;
                case 20:
                    if (fives == 0 || (tens == 0 && fives < 3)) {
                        return false;
                    }
                    if (tens == 0) {
                        fives -= 3;
                    } else {
                        fives--;
                        tens--;
                    }
                    continue;
            }
        }
        return true;
    }
}
