package com.sunilsahoo.dynamicprogramming;

public class BuyStockProblem {

    public static void main(String[] args) {
        BuyStockProblem buyStockProblem = new BuyStockProblem();
        int[] prices = {7,1,5,3,6,4};
        int result = buyStockProblem.maxProfit(prices);
        System.out.println(result);
        result = buyStockProblem.maxProfit2(prices);
        System.out.println(result);
        result = buyStockProblem.maxProfit(prices, 3);
        System.out.println(result);
        result = buyStockProblem.maxProfitBottomUp(prices, 3);
        System.out.println(result);
    }
    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * <p>
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
     * <p>
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     * <p>
     * Input: prices = [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     * <p>
     * <p>
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transactions are done and the max profit = 0.
     * <p>
     * We need to find the largest peak following the smallest valley. We can maintain two variables - minprice and maxprofit
     * corresponding to the smallest valley and maximum profit (maximum difference between selling price and minprice) obtained so far respectively.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }


    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * <p>
     * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * <p>
     * Input: prices = [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     * <p>
     * <p>
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
     * <p>
     * <p>
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e., max profit = 0.
     */

    public int maxProfit2(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    /**
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
     * <p>
     * Find the maximum profit you can achieve. You may complete at most k transactions.
     * <p>
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * <p>
     * Input: k = 2, prices = [2,4,1]
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     * <p>
     * Input: k = 2, prices = [3,2,6,5,0,3]
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * <p>
     * Let profit[t][i] represent maximum profit using at most t transactions up to day i (including day i). Then the relation is:
     * profit[t][i] = max(profit[t][i-1], max(price[i] – price[j] + profit[t-1][j]))
     * for all j in range [0, i-1]
     * profit[t][i] will be maximum of –
     * <p>
     * profit[t][i-1] which represents not doing any transaction on the ith day.
     * Maximum profit gained by selling on ith day. In order to sell shares on ith day, we need to purchase it on any one of [0, i – 1] days. If we buy shares on jth day and sell it on ith day, max profit will be price[i] – price[j] + profit[t-1][j] where j varies from 0 to i-1. Here profit[t-1][j] is best we could have done with one less transaction till jth day.
     */

     int maxProfit(int[] price,
                         int k) {
        int n = price.length;

        // table to store results of subproblems profit[t][i] stores maximum profit using atmost t transactions up to day i (including day i)
        int[][] profit = new int[k + 1][n + 1];

        // For day 0, you can't earn money irrespective of how many times you trade
        for (int i = 0; i <= k; i++)
            profit[i][0] = 0;

        // profit is 0 if we don't do any transation (i.e. k =0)
        for (int j = 0; j <= n; j++)
            profit[0][j] = 0;

        // fill the table in bottom-up fashion
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                int max_so_far = 0;

                for (int m = 0; m < j; m++)
                    max_so_far = Math.max(max_so_far, price[j] -
                            price[m] + profit[i - 1][m]);

                profit[i][j] = Math.max(profit[i][j - 1],
                        max_so_far);
            }
        }

        return profit[k][n - 1];
    }

    /**
     *
     * If k >= n/2, we can have transactions any time, it can be solved by O(n) as Best Time to Buy and Sell Stock II. Otherwise, we can do it in DP.
     *
     * dp[i][j] stands for the maximal profit gained after at most i transactions via prices[0,,j].
     * We use variable profit to compute the right time to start a new transaction of buying stock to minimize the profit loss —i.e. minimizing dp[i-1][j-1]-prices[j] over prices[0,,j]. It is initialized to be 0-prices[0] meaning we start the new transaction of buying stock at prices[0].
     * dp[i][j] compares dp[i][j-1] — with new profit of the current profit value plus the profit from selling at prices[j].
     * @param prices
     * @param k
     * @return
     */
    public int maxProfitBottomUp(int[] prices, int k) {
        int n = prices.length;

//        If k >= n/2, we can have transactions any time, it can be solved by O(n) as Best Time to Buy and Sell Stock II. Otherwise, we can do it in DP.
        if (k>n/2)
        {
            int res = 0;
            for (int i = 1; i < n; i ++)
            {
                res += Math.max(0,prices[i]-prices[i-1]);
            }
            return res;
        }

        int[][] profit = new int[k + 1][n + 1];
        for (int i = 1; i <= k; ++i) {
            int curMax = Integer.MIN_VALUE;
            for (int j = 0; j < n; ++j) {
                profit[i][j + 1] = Math.max(
                        Math.max(profit[i][j], profit[i - 1][j + 1]),
                        prices[j] + curMax);
                curMax = Math.max(curMax, profit[i - 1][j] - prices[j]);
            }
        }
        return profit[k][n];
    }

}
