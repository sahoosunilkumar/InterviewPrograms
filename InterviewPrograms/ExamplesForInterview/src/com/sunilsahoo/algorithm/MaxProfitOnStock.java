package com.sunilsahoo.algorithm;

import java.util.LinkedHashMap;

public class MaxProfitOnStock {
	public static void main(String args[]) {
		int[] stockPriceArr = { 100, 80, 120, 130, 70, 60, 95, 100 };
		System.out.println(
				new MaxProfitOnStock().findMaxProfitStock(stockPriceArr));
		int[] stockPriceArr1 = { 100, 80, 120, 130, 70, 60, 100, 125 };
		System.out
				.println(new MaxProfitOnStock().findMaxProfit(stockPriceArr1));
	}

	/*
	 * Given an array representing prices of the stock on different days, find
	 * the maximum profit that can be earned by performing maximum of one
	 * transaction. A transaction consists of activity of buying and selling the
	 * stock on different or same days. For example in this array - {100, 80,
	 * 120, 130, 70, 60, 100, 125} the price of the stock on day-1 is 100, on
	 * day-2 is 80 and so on. The maximum profit that could be earned in this
	 * window is 65 (buy at 60 and sell at 125). For price array - {100, 80, 70,
	 * 65, 60, 55, 50}, maximum profit that could be earned is 0.
	 * 
	 * 
	 * If you observe above chart carefully, you should be able to notice that:
	 * for any given day, maximum profit obtainable is maximum of 1.Maximum
	 * profit obtained till previous day, 2.Stock price on that day - minimum
	 * stock price so far. Therefore, to find out the maximum profit obtainable
	 * for a given window, all we need to is to keep track of minimum stock
	 * price seen so far (starting from day-1) and maximum profit obtained so
	 * far. Maximum profit obtained so far can be computed using above
	 * observation. Maximum profit obtained so far is initialized to 0 and
	 * minimum stock price seen so far is initialized to MAX_VALUE.
	 * 
	 * Order of the Algorithm Time Complexity is O(n) Space Complexity is O(1)
	 * 
	 */
	private LinkedHashMap<String, Integer> findMaxProfitStock(
			int[] stockPriceArr) {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		int maxProfit = 0;
		int minStockPrice = stockPriceArr[0];
		for (int i = 1; i < stockPriceArr.length; i++) {
			if ((stockPriceArr[i] < minStockPrice)) {
				minStockPrice = stockPriceArr[i];
			}
			if ((stockPriceArr[i] - minStockPrice) > maxProfit) {
				maxProfit = stockPriceArr[i] - minStockPrice;
				map.put("Day", i);
				map.put("Profit", maxProfit);
			}

		}
		return map;
	}

	/**
	 * Given an array of stock prices, find the maximum profit that can be
	 * earned by performing multiple non-overlapping transactions (buy and
	 * sell). Example: {100, 80, 120, 130, 70, 60, 100, 125} Profit: 115
	 * 
	 * Time Complexity is O(n) Space Complexity is O(1)
	 * 
	 * @return
	 */
	private int findMaxProfit(int[] stockPriceArr) {
		int maxProfit = 0;
		for (int i = 1; i < stockPriceArr.length; i++) {
			if ((stockPriceArr[i] - stockPriceArr[i - 1]) > 0) {
				maxProfit += (stockPriceArr[i] - stockPriceArr[i - 1]);
			}
		}
		return maxProfit;
	}

}
