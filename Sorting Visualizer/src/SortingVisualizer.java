import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class SortingVisualizer {
	
	private Window window;
	private Graphics g;
	private int barNum;
	private BufferStrategy bs;
	private Bar[] bars;
	private int comparisons = 0;
	private int swaps = 0;
	
	private int maxNum = 900;
	private int scale = 1;

	
	public static int updateCap;
	
	public static final Color white = new Color(255, 255, 255);
	public static final Color black = new Color(0, 0, 0);
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	
	SortingVisualizer(int fps) {
		//Max: 500/ 150
		updateCap = fps;
		createWindow();
		this.bars = makeRandomBars(50);
	}
	//creates window
	private void createWindow() {
		this.window = new Window(WIDTH, HEIGHT);
		this.g = window.getGraphics();
		this.bs = window.getBuffer();
	}
	//used to initialize new sorts
	private void initialize(Bar[] bars) {
		clearBars();
		this.bars = copyBarList(bars);
		this.barNum = this.bars.length;
		showBars(bars);
	}
	private Bar[] copyBarList(Bar[] barList) {
		int len = barList.length;
		Bar[] newBars = new Bar[len];
		for (int i=0;i<len;i++) {
			newBars[i] = new Bar(g, scale, len, barList[i].getLength(), i, bs);
		}
		return newBars;
	}
	private void clearBars() {
		this.comparisons = 0;
		this.swaps = 0;
		g.clearRect(0, 0, 1000, 1000);
		for (int i=0;i<barNum;i++) {
			this.bars[i].eraseBar();
		}
	}
	private void showBars(Bar[] barList) {
		for (int i=barList.length - 1;i>=0;i--) {
			barList[i].showBar();
		}
	}
	//public methods to make lists and sort them
	public Bar[] makeRandomBars(int length) {
		clearBars();
		Bar[] bars = new Bar[length];
		for (int i=0;i<length;i++) {
			int len = (int) (maxNum * Math.random());
			bars[i] = new Bar(g, scale, length, len, i, bs);
		}
		return bars;
	}
	public Bar[] makeBarsFromArray(int[] numList) {
		clearBars();
		int length = numList.length;
		Bar[] bars = new Bar[length];
		for (int i=0;i<length;i++) {
			bars[i] = new Bar(g, scale, length, numList[i], i, bs);
		}
		return bars;
	}
	public void setBars(Bar[] barList) {
		this.bars = barList;
		this.barNum = this.bars.length;
	}
	public Bar[] doBubbleSort(Bar[] numList) {
		initialize(numList);
		Bar[] sorted = bubbleSort(this.bars);
		finish();
		return sorted;
	}

	public Bar[] doSelectionSort(Bar[] numList) {
		initialize(numList);
		Bar[] sorted = selectionSort(this.bars);
		finish();
		return sorted;
	}
	public Bar[] doMergeSort(Bar[] numList) {
		initialize(numList);
		Bar[] sorted = mergeSort(this.bars);
		finish();
		return sorted;
	}
	public Bar[] doQuickSort(Bar[] numList) {
		initialize(numList);
		Bar[] sorted = quickSort(this.bars);
		finish();
		return sorted;
	}
	public Bar[] doBubbleSort() {
		Bar[] sorted = bubbleSort(this.bars);
		finish();
		return sorted;
	}

	public Bar[] doSelectionSort() {
		Bar[] sorted = selectionSort(this.bars);
		finish();
		return sorted;
	}
	public Bar[] doMergeSort() {
		Bar[] sorted = mergeSort(this.bars);
		finish();
		return sorted;
	}
	public Bar[] doQuickSort() {
		Bar[] sorted = quickSort(this.bars);
		finish();
		return sorted;
	}
	public void resetSort() {
		secondsDelay(2);
		clearBars();
	}
	//optional public methods to add delays between sorts
	public void secondsDelay(int seconds) {
		double firstTime = 0.0;
		double nextTime = System.nanoTime() / 1000000000.0;
		double skippedTime = 0.0;
		double elapsedTime = 0.0;
		boolean running = true;
		while(running) {
			firstTime = System.nanoTime() / 1000000000.0;
			elapsedTime = firstTime - nextTime;
			nextTime = firstTime;
			skippedTime += elapsedTime;
			if (skippedTime >= seconds) {
				running = false;
			}
		}
	}

	private void printList(Bar[] newBars) {
		System.out.println(Arrays.toString(newBars));
	}
	//Swap method. Upper bar has the lower index in array.
	private void swapBars(Bar upperBar, Bar lowerBar) {
		if (!(upperBar.equals(lowerBar))) {
			swaps++;
			int upperIndex = Arrays.asList(this.bars).indexOf(upperBar);
			int lowerIndex = Arrays.asList(this.bars).indexOf(lowerBar);
			upperBar.swap(lowerBar);
			this.bars[upperIndex] = lowerBar;
			this.bars[lowerIndex] = upperBar;
		}
	}
	//Prints sorted array to console and displays the number of comparisons and swaps done during the sort.
	private void finish() {
		printList(this.bars);
		g.setColor(black);
		int x = this.bars[barNum / 2].getLength();
		x = ((1000 - x) / 2) + x - 50;
		g.drawString("Done", x, 365);
		g.drawString("Comparisons: " + comparisons, x, 385);
		g.drawString("Swaps: " + swaps, x, 405);
		bs.show();
	}
	//Sorting Algorithms
	private Bar[] bubbleSort(Bar[] numList) {
		boolean sorted = false;
		int lastSorted = numList.length - 1;
		while (!sorted) {
			boolean swapped = false;
			for (int i=0;i<lastSorted;i++) {
				Bar upperBar = numList[i];
				Bar lowerBar = numList[i + 1];
				comparisons++;
				if (upperBar.isGreaterThan(lowerBar)) {
					swapBars(upperBar, lowerBar);
					swapped = true;
				} else {
					upperBar.confirmSwitch(lowerBar);
				}
			}
			if (!swapped) {
				for (int i=lastSorted - 1;i>=0;i--) {
					numList[i].confirmSorted();
				}
				sorted = true;
			}
			numList[lastSorted].confirmSorted(); 
			lastSorted--;
			
		}
		return numList;
	}
	private Bar[] selectionSort(Bar[] numList) {
		int n = numList.length;
		for (int i=0;i<n - 1;i++) {
			int min = i;
			for (int j=i+1;j<n;j++) {
				comparisons++;
				Bar minBar = numList[min];
				Bar newBar = numList[j];
				if (minBar.isGreaterThan(newBar)) {
					minBar.resetColor();
					min = j;
				} else {
					newBar.resetColor();
				}
			}
			if (i != min) {
				swapBars(numList[i], numList[min]);
			}
			numList[i].confirmSorted();
		}
		numList[n - 1].confirmSorted();
		return numList;
	}
	private Bar[] mergeSort(Bar[] numList) {
		int n = numList.length;
		if (n > 1) {
			Bar[] upperBars = Arrays.copyOfRange(numList, 0, n / 2);
			Bar[] lowerBars = Arrays.copyOfRange(numList, n / 2, n);
			upperBars = mergeSort(upperBars);
			lowerBars = mergeSort(lowerBars);
			return merge(upperBars, lowerBars);
		} else {
			return numList;
		}
	}
	private Bar[] merge(Bar[] upper, Bar[] lower) {
		int startIndex = Arrays.asList(this.bars).indexOf(upper[0]);
		int endIndex = Arrays.asList(this.bars).indexOf(lower[lower.length - 1]);
		int upperLen = upper.length;
		int lowerLen = lower.length;
		int highest = upperLen;
		boolean lastMerge = false;
		if (upperLen < lowerLen) {
			highest = lowerLen;
		}
		if (upperLen + lowerLen == this.bars.length) {
			lastMerge = true;
		}
		Bar[] newBars = new Bar[upperLen + lowerLen];
		for (int i=0, k=0;i<upperLen || k<lowerLen;) {
			if (i >= upperLen) {
				swapBars(bars[startIndex + i + k], lower[k]);
				if (lastMerge)
					bars[startIndex + i + k].confirmSorted();
				newBars[i + k] = lower[k++];
			} else if (k >= lowerLen) {
				swapBars(bars[startIndex + i + k], upper[i]);
				if (lastMerge)
					bars[startIndex + i + k].confirmSorted();
				newBars[i + k] = upper[i++];
			} else if (upper[i].isGreaterThan(lower[k])) {
				comparisons++;
				swapBars(bars[startIndex + i + k], lower[k]);
				if (lastMerge)
					bars[startIndex + i + k].confirmSorted();
				newBars[i + k] = lower[k++];
			} else {
				comparisons++;
				swapBars(bars[startIndex + i + k], upper[i]);
				if (lastMerge)
					bars[startIndex + i + k].confirmSorted();
				newBars[i + k] = upper[i++];
			}
		}
		return newBars;
	}
	private Bar[] quickSort(Bar[] numList) {
		printList(numList);
		quickSort(0, barNum - 1);
		for (int i=0;i<barNum;i++) {
			if (!numList[i].isSorted()) {
				numList[i].confirmSorted();
			}
		}
		return this.bars;
	}
	private void quickSort(int low, int high) {
		if (low < high) {
			int j = partition(low, high);
			quickSort(low, j - 1);
			quickSort(j + 1, high);
		} else if (low==high){
			Bar sortedBar = this.bars[low];
			sortedBar.confirmSorted();
		}
	}
	private int choosePivot(int low, int high) {
		int pivot = 0;
		return pivot;
	}
	private int chooseRandomPivot(int low, int high) {
		int pivot = (int) (Math.random() * (high - low));
		return pivot;
	}
	private int partition(int low, int high) {
		int i = low + 1;
		int j = high;
		int p = choosePivot(low, high + 1);
		Bar pivotBar = this.bars[low + p];
		swapBars(pivotBar, this.bars[low]);
		while (i<=j) {
			Bar upperBar = this.bars[i];
			Bar lowerBar = this.bars[j];
			comparisons++;
			if (upperBar.isGreaterThan(pivotBar)) {
				swapBars(upperBar, lowerBar);
				j--;
			} else {
				upperBar.resetColor();
				i++;
			}
		}
		swapBars(pivotBar, this.bars[j]);
		pivotBar.confirmSorted();
		return j;
	}
	private void bubbleDriver() {
		this.doBubbleSort(this.bars);
		this.resetSort();
	}
	private void selectDriver() {
		this.doSelectionSort(this.bars);
		this.resetSort();
	}
	private void mergeDriver() {
		this.doMergeSort(this.bars);
		this.resetSort();
	}
	private void quickDriver() {
		doQuickSort(this.bars);
		//this.resetSort();
	}
	//Instructions:
	//1) Initialize an instance of SortingVisualizer with the number of frames per second as the parameter.
	//2) Make either an array of Bar objects or an array of integers using the makeRandomBars, makeRandomInts, or your own method of choice.
	//3) OPTIONAL: Call setBars on the SortingVisualizer with the array as the parameter.
	//4) Call the sort method with either no parameters (if you did step 3) or the array as the parameter.
	//5) OPTIONAL: Call resetSort to do a different sort.
	//6) OPTIONAL: Call delay or secondsDelay methods to increase the time between sorts.
	//Happy Sorting!
	public static void driver() {
		SortingVisualizer visual = new SortingVisualizer(100);
		Bar[] unsorted = visual.makeRandomBars(50);
		visual.setBars(unsorted);
		visual.bubbleDriver();
		visual.setBars(unsorted);
		visual.selectDriver();
		visual.setBars(unsorted);
		visual.mergeDriver();
		visual.setBars(unsorted);
		visual.quickDriver();
		System.out.println("Done test.");
	}
	//driver
	public static void main(String[] args) {
		driver();
	}
}