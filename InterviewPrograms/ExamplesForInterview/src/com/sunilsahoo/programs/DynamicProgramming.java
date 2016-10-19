package com.sunilsahoo.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.sunilsahoo.algorithm.Utility;

public class DynamicProgramming {
	public static void main(String[] args) {
		DynamicProgramming dp = new DynamicProgramming();
		Set<String> dictionary = new HashSet<String>(Arrays.asList("arrays",
				"dynamic", "heaps", "IDeserve", "learn", "learning", "linked",
				"list", "platform", "programming", "stacks", "trees"));
		String word = "IDeservelearningplatform";
		System.out.println(dp.wordBreak(dictionary, word));
		int[] input = { 2, -9, 5, 1, -4, 6, 0, -7, 8 };
		System.out.println(dp.findMaxSubarraySum(input));

		int matrix[][] = { { 0, 1, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1, 1 },
				{ 0, 1, 1, 1, 0, 0 }, { 1, 1, 1, 1, 0, 0 },
				{ 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 1 } };
		System.out.println(dp.maximumSizeSquareSubmatrixWithAllOnes(matrix));

		int[] binaryArray = { 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1,
				0, 1, 1, 1, 1, 0 };
		System.out.println(
				"max position will be :" + dp.getRequiredIndex(binaryArray));
	}

	/*
	 * Given a dictionary of words and a string of characters, find if the
	 * string of characters can be broken into individual valid words from the
	 * dictionary. Example: Dictionary: arrays, dynamic, heaps, IDeserve, learn,
	 * learning, linked, list, platform, programming, stacks, trees String :
	 * IDeservelearningplatform Output : true Because the string can be broken
	 * into valid words: IDeserve learning platform
	 * 
	 * Time Complexity is O(n^2) in worst case Space Complexity is O(n)
	 */
	private boolean wordBreak(Set<String> dictionary, String words) {
		if (words == null || words.length() == 0) {
			return true;
		}
		int n = words.length();
		boolean[] validWords = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (dictionary.contains(words.substring(0, i + 1))) {
				validWords[i] = true;
			}
			if (validWords[i] == true) {
				for (int j = i + 1; j < n; j++) {
					if (dictionary.contains(words.substring(i + 1, j + 1))) {
						validWords[j] = true;
					}
					if (j == n - 1 && validWords[j] == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * 1. Start with curSum = 0 and maxSum = 0. 2. Traverse the array elements,
	 * from i = 0 to n-1, where n is the length of the array. a. Keep adding
	 * array elements to curSum till curSum >= 0 and update maxSum to curSum if
	 * maxSum < curSum. b. As soon as curSum becomes < 0, reset curSum = 0. 3.
	 * Finally return maxSum. This is known as Kadane's algorithm.
	 * 
	 * Given an array of unordered positive and negative integers, find the
	 * maximum subarray sum in the array. For example: Array: {2, -9, 5, 1, -4,
	 * 6, 0, -7, 8} Output: Maximum subarray sum is 9 Time Complexity is O(n)
	 * Space Complexity is O(1)
	 */
	int findMaxSubarraySum(int[] input) {
		int maxSum = 0;
		int curSum = 0;
		boolean hasAllNegativeNumbers = true;
		int maxNegativeSum = Integer.MIN_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (hasAllNegativeNumbers && input[i] >= 0) {
				hasAllNegativeNumbers = false;
			} else if (hasAllNegativeNumbers && input[i] < 0
					&& maxNegativeSum < input[i]) {
				maxNegativeSum = input[i];
			}
			curSum += input[i];
			if (curSum < 0) {
				curSum = 0;
			}
			if (maxSum < curSum) {
				maxSum = curSum;
			}
		}
		return hasAllNegativeNumbers ? maxNegativeSum : maxSum;
	}

	/*
	 * Time Complexity is O(mn) Space Complexity is O(mn)
	 */
	int maximumSizeSquareSubmatrixWithAllOnes(int[][] matrix) {
		int maxSize = 0;
		int r = matrix.length;
		int c = matrix[0].length;
		int[][] table = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 || j == 0) {
					table[i][j] = matrix[i][j];
					maxSize = table[i][j] > maxSize ? table[i][j] : maxSize;
				} else if (matrix[i][j] == 0) {
					table[i][j] = 0;
				} else {
					table[i][j] = min(table[i - 1][j - 1], table[i - 1][j],
							table[i][j - 1]) + 1;
					maxSize = table[i][j] > maxSize ? table[i][j] : maxSize;
				}
			}
		}
		return maxSize;
	}

	int min(int i, int j, int k) {
		return i <= j && i <= k ? i : (j <= i && j <= k ? j : k);
	}

	private void buildCountArray(int[] array, int[] countArray) {
		int runningOneCount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				runningOneCount += 1;
				countArray[i] = -1;
			} else {
				countArray[i] = runningOneCount;
				runningOneCount = 0;
			}
		}
		System.out.println(Utility.toString(countArray));
		runningOneCount = 0;
		for (int i = array.length - 1; i > -1; i--) {
			if (array[i] == 1) {
				runningOneCount += 1;
				countArray[i] = -1;
			} else {
				countArray[i] += runningOneCount;
				runningOneCount = 0;
			}
		}
	}

	/*
	 * Given a binary array, find the index of 0 such that when that 0 is
	 * replaced with 1 results in longest continuous sequence of 1's. For
	 * example, for array {0,1,1,1,0,1,0} replacing 0 at 0th index with 1
	 * results in a sequence of 1's with length 4 and replacing 0 at index 4
	 * with 1 results in a sequence of 1's with length 5. Hence for this input
	 * array, output returned should be 4. For array
	 * {0,0,1,1,1,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,1,1,0} longest sequence of 1's
	 * is obtained when we replace 0 at index 12 with 1.
	 * 
	 * 
	 * Time Complexity is O(n) Space Complexity is O(n)
	 */
	public int getRequiredIndex(int[] array) {
		int[] countArray = new int[array.length];
		buildCountArray(array, countArray);
		System.out.println(Utility.toString(countArray));
		int maxCount = -1, reqIndex = 0;
		for (int i = 0; i < countArray.length; i++) {
			if (countArray[i] > maxCount) {
				reqIndex = i;
				maxCount = countArray[i];
			}
		}
		return reqIndex;
	}

	/*
	 * Time Complexity is O(n) Space Complexity is O(1)
	 */
	int getRequiredIndexOtimized(int[] binaryArray) {
		int prevZeroIndex = -1, prevPrevZeroIndex = -1;
		int maxLength = -1, requiredIndex = -1, currLength = -1;
		for (int i = 0; i < binaryArray.length; i++) {
			if (binaryArray[i] == 0) {
				if (prevPrevZeroIndex != -1) {
					currLength = i - prevPrevZeroIndex - 1;
					if (currLength > maxLength) {
						maxLength = currLength;
						requiredIndex = prevZeroIndex;
					}
				}
				prevPrevZeroIndex = prevZeroIndex;
				prevZeroIndex = i;
			}
		}
		if (maxLength == -1) {
			if (prevPrevZeroIndex != -1) {
				if (prevZeroIndex > (binaryArray.length - prevPrevZeroIndex
						- 1)) {
					requiredIndex = prevPrevZeroIndex;
				} else {
					requiredIndex = prevZeroIndex;
				}
			} else {
				requiredIndex = prevZeroIndex;
			}
		}
		return requiredIndex;
	}

	/*
	 * Given a set of items, each with weight and benefit, determine the items
	 * to include in the knapsack so that the total weight is less than or equal
	 * to a given weight limit and the total benefit is maximized. For example,
	 * if we are given following four items with their corresponding weights and
	 * benefits which items should we include in the knapsack to maximize the
	 * benefit. The weight limit for this knapsack is 10? As you can verify, the
	 * items to include in the knapsack(with the weight limit of 10) to get the
	 * maximum benefit are item #1, item #2 and item #4. Maximum benefit
	 * obtained in that case is 19 and the weight of the knapsack becomes 9
	 * which is within the given weight limit.
	 * 
	 * Time Complexity is O(wn) (w: weight limit, n: total number of items
	 * given) Space Complexity is O(wn)
	 */
	public int max(int a, int b) {
		if (a > b)
			return a;
		return b;
	}

	public int min(int a, int b) {
		if (a < b)
			return a;
		return b;
	}

	public class ListWithBenefit {
		ArrayList<Integer> listItems;
		int benefit;

		public ListWithBenefit(int benefit) {
			listItems = new ArrayList<>();
			this.benefit = benefit;
		}

		public ListWithBenefit(ListWithBenefit obj) {
			listItems = new ArrayList<>();
			for (

			int i = 0; i < obj.listItems.size(); i++) {
				listItems.add(obj.listItems.get(i));
			}
			this.benefit = obj.benefit;
		}
	}

	ListWithBenefit findOptimalItems(int w, int n, int[] val, int[] weight,
			ListWithBenefit[][] optimalKnapsack) {
		if (w == 0 || n == weight.length) {
			optimalKnapsack[w][n] = new ListWithBenefit(0);
			return optimalKnapsack[w][n];
		}
		if (weight[n] > w)
			return (optimalKnapsack[w][n + 1] == null)
					? findOptimalItems(w, n + 1, val, weight, optimalKnapsack)
					: optimalKnapsack[w][n + 1];
		ListWithBenefit include_n_benefit = (optimalKnapsack[w - weight[n]][n
				+ 1] == null)
						? new ListWithBenefit(findOptimalItems(w - weight[n],
								n + 1, val, weight, optimalKnapsack))
						: new ListWithBenefit(
								optimalKnapsack[w - weight[n]][n + 1]);
		include_n_benefit.listItems.add(weight[n]);
		include_n_benefit.benefit += val[n];
		ListWithBenefit exclude_n_benefit = (optimalKnapsack[w][n + 1] == null)
				? new ListWithBenefit(findOptimalItems(w, n + 1, val, weight,
						optimalKnapsack))
				: new ListWithBenefit(optimalKnapsack[w][n + 1]);
		if (include_n_benefit.benefit > exclude_n_benefit.benefit) {
			optimalKnapsack[w][n] = new ListWithBenefit(include_n_benefit);
			return include_n_benefit;
		}
		optimalKnapsack[w][n] = new ListWithBenefit(exclude_n_benefit);
		return exclude_n_benefit;
	}

	public int findMaximumBenefit(int w, int n, int[] val, int[] weight) {
		if (w == 0 || n == weight.length) {
			return 0;
		}
		if (weight[n] > w)
			return findMaximumBenefit(w, n + 1, val, weight);
		int includeCaseBenefit = val[n]
				+ findMaximumBenefit(w - weight[n], n + 1, val, weight);
		int excludeCaseBenefit = findMaximumBenefit(w, n + 1, val, weight);
		return max(includeCaseBenefit, excludeCaseBenefit);
	}

}
