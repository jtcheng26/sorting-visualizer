class MergeDemo {
	public static void smallDemo() {
		SortingVisualizer s = new SortingVisualizer(5);
		Bar[] unsorted = s.makeRandomBars(10);
		s.doMergeSort(unsorted);
	}
	public static void demo() {
		SortingVisualizer s = new SortingVisualizer(100);
		Bar[] unsorted = s.makeRandomBars(50);
		s.doMergeSort(unsorted);
	}
	public static void bigDemo() {
		SortingVisualizer s = new SortingVisualizer(250);
		Bar[] unsorted = s.makeRandomBars(100);
		s.doMergeSort(unsorted);
	}
	public static void arrayDemo() {
		SortingVisualizer s = new SortingVisualizer(10);
		int[] numbers = {440, 230, 701, 907, 512, 405, 607, 202, 301, 579, 924, 395};
		Bar[] unsorted = s.makeBarsFromArray(numbers);
		s.doMergeSort(unsorted);
	}
	public static void main(String[] args) {
		demo();
		smallDemo();
		bigDemo();
		arrayDemo();
	}
}