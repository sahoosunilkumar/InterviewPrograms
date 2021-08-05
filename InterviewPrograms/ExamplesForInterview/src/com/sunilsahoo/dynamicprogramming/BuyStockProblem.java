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

        result = buyStockProblem.maxProfit_k_any(3, prices);
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


    /**
     * Let’s see an example and how our code works: say prices=[3,2,6,5,0,3]
     * dp[1][1]=0, profit=-2: find a better buying point than prices[0]
     * dp[1][2]=4, profit=-2: find a good deal which increases our profit to 4
     * dp[1][3]=4, profit=-2
     * dp[1][4]=4, profit=0: find a better buying point than prices[1]
     * dp[1][5]=4, profit=0: the current buying point results in profit==3 which is smaller than the best profit==4 so far
     * dp[2][1]=0, profit=-2
     * dp[2][2]=4, profit=-2
     * dp[2][3]=4, profit=-1: the previous profit gained dp[1][2] is equal to 4. Now we could start another transaction of buying at prices[3].
     * dp[2][4]=4, profit=4: the new transaction of buying at prices[3] and selling at prices[4] decrease our profit; however we find a better buying point at prices[4] instead
     * dp[2][5]=7,profit=4:the new transaction of buying at prices[4] and selling at prices[5] does increase our profit
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        if (k>n/2)
        {
            int res = 0;
            for (int i = 1; i < n; i ++)
            {
                res += Math.max(0,prices[i]-prices[i-1]);
            }
            return res;
        }

        int[][] dp = new int[k+1][n];
        for (int i = 1; i <= k; i ++)
        {
            int profit = 0-prices[0];
            for (int j = 1; j < n; j ++)
            {
//                dp[i][j-1] = Yesterday I had sold Item & I rest today
//                profit+prices[j] = I sell stock today
                dp[i][j] = Math.max(dp[i][j-1], profit+prices[j]);
//                dp[i-1][j-1] = Yesterday I Sold Item
//                dp[i-1][j-1]-prices[j] = Yesterday I Sold Item, today I Purchase

                profit = Math.max(profit, dp[i-1][j-1]-prices[j]);
            }
        }

        return dp[k][n-1];
    }


    /**
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *               max( choose rest  ,          choose sell      )
     *
     * Explanation: I don’t hold stocks today. There are two possibilities:
     * 1) Either I didn’t hold stocks yesterday, and then choose to rest today, so I still don’t hold stocks today.
     * 2) Either I held stocks yesterday, but today I chose to sell, so I don't hold stocks today.
     *
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *               max( choose rest  ,            choose buy         )
     *
     * Explanation: Today I hold stocks. There are two possibilities:
     * 1) Either I held stocks yesterday and chose to rest today, so I still hold stocks today.
     * 2) Either I didn't hold stocks yesterday, but today I chose to buy, so today I hold stocks.
     * @param max_k
     * @param prices
     * @return
     */
    int maxProfit_k_any(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2) {
            return maxProfit_k_inf(prices);
        }

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                break;}
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }

    int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

}
