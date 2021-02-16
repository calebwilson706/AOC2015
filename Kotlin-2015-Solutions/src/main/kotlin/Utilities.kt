fun getFrequencyMap(list: List<*>) = list.groupingBy { it }.eachCount()
