
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Класс с одним Методом, который организует Список Объектов, который он считывает из текстового файла, расположенного
 * на компьютере. Т.е. по адресу текстового файла читает его и формирует из него Список Объектов (Игр). Применяется
 * в главном методе в Классе MyGameSearcher при формировании Массива Объектов */

public class GamesReader {

	// Наш метод который будет считывать данные, формировать Коллекцию Игр и сортировать ее применяя наш Компаратор (это вконце) из Класса MyComp
	/* аргументом на место filename должен передаваться адрес текстового файла */
	
	public static List<TryStuff> readGamesFromFile(String filename) {
			
			List<TryStuff> games = new ArrayList<>();

			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				
				/* заводим 3 переменные, перед циклом, значит они "глобальные" по отношению к циклу. В result будем объеденять
				 * строки, относящиеся к одному объекту */
				
				String line;
				String result = "";
				int count = 0;
				
				/* каждая итерация цикла это чтение одной стоки. Соответственно, ставим условия и тем самым
				 * что-то игнорируем и начинаем цикл заново, что-то обрезаем и т.д. */
				
				while ((line = br.readLine()) != null) {
					
					// считываем Строку без учета пробелов вначале или вконце
					
					line = line.trim();
					
					// если Строка пустая или начинается с // (но не заканчивается на "*/") - пропускаем ее
					
					if  (line.isEmpty() || (line.startsWith("//") && (!line.endsWith("*/"))) ) {
						continue;
					}
													
					
	/////////////  Блок, реализующий игнорирование кода в /* .... */  /////////////
					
					
					if (   (line.startsWith("/*")) && (line.endsWith("*/"))  ) {

						continue;
					}
					
					if (line.startsWith("/*")) {
						
						count=1;
						continue;
					}
					
					if (  ((count==1) && (line.endsWith(",")))     ||

					 ((count==1) && (line.endsWith(";")))          ||

					 ((count==1) && (line.isEmpty()))              ||

					 ((count==1) && (line.startsWith("//")) && (!line.endsWith("*/")))   )   {  

						continue;
					}
					
					if ((count==1) && (line.endsWith("*/"))) {

						count=0;
						continue;
					}

	///////////////////////////////////////////////////////////////////////////////				
							
					// если Строка кончается на "," значит дальше будет адрес Фотки, надо объеденять строки
					
					if (line.endsWith(",")) {
						result += line.trim() + " ";
						continue;
					}
					
					// если Строка кончается на ";" значит это окончательный конец строки, надо заканчивать объеденение
					
					if (line.endsWith(";")) {
						line = line.substring(0, line.length()-1);  // удаляем эти ;
						result += line.trim() + " ";
						line = result;
						result = "";
						//continue;
					}
					
					/* разделяем Строку на подстроки, "," - разделитель и упаковываем их в Массив Строк */					
					/* т.е создаем Массив Строк, в котором каждый элемент (каждая строка) это значение нашего Поля
					 * передаваемое Конструктору. В зависимости от количества полей (переменная h), выбирается соответствующий 
					 * Конструктор, их всего 6 */
					
					String[] parts = line.split(",");
					
					int h = parts.length; // переменная для отслеживания количества Полей считанных для одного Объекта.
					
					// в каждой Подстроке удаляем Пробелы (если были) вначале или вконце
					
					for (int i=0; i<h; i++) {
						parts[i] = parts[i].trim();
					}
					
					// если Подстрока начинается с ", удаляем этот символ
					
					for (int i=0; i<h; i++) {
						if (parts[i].startsWith("\"")) {
							parts[i] = parts[i].substring(1);
						}
					}
					
					// если Подстрока кончается на ", удаляем этот символ
					
					for (int i=0; i<h; i++) {
						if (parts[i].endsWith("\"")) {
							parts[i] = parts[i].substring(0, parts[i].length()-1);
						}
					}
			
			///////////////////////// блок формирования Объектов из считаных строк ////////////////////
					
				/* в зависимости от количества считанных частей (h) применяем соответствующий Конструктор */
					
					if (h==1) { // 3 фотки по умолчанию
						
						String name = parts[0];
						
						TryStuff person = new TryStuff (name);
						games.add(person);
					}
					
					else if (h==2) { // 3 фотки по умолчанию
						
						String name = parts[0];
						String creator = parts[1];
						
						TryStuff person = new TryStuff (name, creator);
						games.add(person);
					}
					
					else if (h==3) { // 3 фотки по умолчанию
						
						String name    = parts[0];
						String creator = parts[1];
						String year    = parts[2];
						
						TryStuff person = new TryStuff (name, creator, year);
						games.add(person);
					}
					
					else if (h==4) { // 3 фотки по умолчанию
						
						String name    = parts[0];
						String creator = parts[1];
						String mapper  = parts[2];
						String year    = parts[3];
						
						TryStuff person = new TryStuff (name, creator, mapper, year);
						games.add(person);
					}
					
					else if (h==5) { // 3 фотки по умолчанию
						
						String name    = parts[0];
						String creator = parts[1];
						String mapper  = parts[2];
						String year    = parts[3];
						String comment = parts[4];
						
						TryStuff person = new TryStuff (name, creator, mapper, year, comment);
						games.add(person);
					}
					
					/* благодаря этому блоку else стало возможно просматривать бесконечное число фоток
					 * одной игры. Т.е. сколько фоток будет столько и прочитаем */
					
				 	else { // 1 и более кастомных фотки
						
						String name    = parts[0];
						String creator = parts[1];
						String mapper  = parts[2];
						String year    = parts[3];
						String comment = parts[4];
						
						String[] pik = new String[h-5]; // создаем массив строк для Адресов фоток
						
						/* заполняем Массив pik только Адресами фоток, считанных из файла */
						
						for (int i=5; i<parts.length; i++) {
							
							pik [i-5] = TryStuff.addres + parts[i]; 
						}

						/* создаем объект используя "универсальный" конструктор */
						
						TryStuff person = new TryStuff (name, creator, mapper, year, comment, pik);
						games.add(person); // добавляем объект в список games
					}

				} // конец цикла while
				
				/* пробовал вводить это условие по аналогии с закрытием потока при чтени инфы
				 * из интернета */
				
				//br.close();
				
			} 	catch (IOException e) {
				e.printStackTrace();
			}
			
			// вызываем Метод sort и передаем ему наш Компаратор, это сортирует нашу Коллекцию (либо по Алфавиту либо по Году выпуска)
			
			games.sort(new MyNameComp());
			//games.sort(new MyYearComp());
			
			return games;
			
	} // конец Метода

} // конец Класса
