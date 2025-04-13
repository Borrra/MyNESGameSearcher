import java.util.Arrays;
import java.util.Comparator;

	//создаем Компараторы для объектов типа TryStuff

	/* Компаратор для сортировки объектов (игр) по Именам */

	class MyNameComp implements Comparator <TryStuff>    {
		
		public int compare (TryStuff a, TryStuff b) {
			
			TryStuff aStr, bStr;
			aStr = a;
			bStr = b;
			return aStr.getName().compareTo(bStr.getName());
		}
	}
	
	/* Компаратор для сортировки объектов (игр) по Годам */
	
	class MyYearComp implements Comparator <TryStuff>    {
		
		public int compare (TryStuff a, TryStuff b) {
			
			TryStuff aStr, bStr;
			aStr = a;
			bStr = b;
			return aStr.getYear().compareTo(bStr.getYear());
		}
	}
	
	//* создаем Компаратор для объектов типа WebPhotoFolderDownload, но по-моему он не работает! */

	class MyWebNameComp implements Comparator <WebPhotoFolderDownload>    {
			
		@Override
		public int compare (WebPhotoFolderDownload a, WebPhotoFolderDownload b) {
			
			/* сначла сравниваем по Имени */
			
			int nameComparison = a.getName().compareTo(b.getName());
			
			if (nameComparison != 0) {
				
				return nameComparison;
			}
			
			/* если Имена равны, сравниваем по отсортированным адресам фоток (по массиву) */
			
			String[] sortedA = Arrays.copyOf(a.getPhotoAddres(), b.getPhotoAddres().length);
			String[] sortedB = Arrays.copyOf(b.getPhotoAddres(), a.getPhotoAddres().length);
			
			Arrays.sort(sortedA);
			Arrays.sort(sortedB);
			
			return Arrays.compare(sortedA, sortedB);
		}
	}
	
	/* вот этот Компаратор заработал, он сортирует имена фотографий в Папках на компе и при 
	 * формировании папки General (я вставляю ее в Массив Git Hub вручную).
	 * Для него нужно чтобы строки начинались с цифры (1.jpg, 15.jpg и т.д.) */
	
	class MyStringNameComp implements Comparator<String> {
		
		@Override
		
		public int compare(String s1, String s2) {
			
			// Extract the leading number from both strings
			
			Integer num1 = extractLeadingNumber(s1);
			Integer num2 = extractLeadingNumber(s2);

			// Compare based on extracted numbers
			
			if (num1 != null && num2 != null) {
				
				return num1.compareTo(num2);
				
			} else if (num1 != null) {
				
			return -1; // s1 comes before s2 if s1 starts with a number
			
			} else if (num2 != null) {

				return 1; // s2 comes after s1 if s2 starts with a number
				
			} else {

				return s1.compareTo(s2); // If neither starts with a number, compare lexicographically
			}
		}

		private Integer extractLeadingNumber(String str) {

			Integer ku = 100;
			
			String adr = WebPhotoFolderDownload.getLastPartStartingWith(str, '/'); // должно оставить только "11.jpg" от адреса
			
			String norm = adr.substring(0, adr.indexOf('.')); // берем только номер фотки (если это номер)
			
			/* если название фотки это число от 1 до 99 */
			
			if (norm.length()<3 && isInteger(norm)) {
				
				ku = Integer.parseInt (norm);
			}
			
			return ku;
			
//			StringBuilder number = new StringBuilder();
//				
//			for (char c : str.toCharArray()) {
//				
//				if (Character.isDigit(c)) {
//					
//					number.append(c);
//						
//				} else {
//					
//					break; // Stop at the first non-digit character
//				}
//			}
//			
//			return number.length() > 0 ? Integer.valueOf(number.toString()) : null;

		}
		
		/* добавил этот метод для определения число ли это или нет
		 * использую выше, для проверки названия фотки */
		
		boolean isInteger (String st) {
			
			try {
				
				Integer.parseInt (st);
				
				return true;
				
			} catch (NumberFormatException e) {
				
				return false;
			}
		}
		
	} // конец Класса MyStringNameComp
