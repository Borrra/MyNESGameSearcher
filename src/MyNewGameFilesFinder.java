
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import java.awt.Frame;

/* это новая версия моего Класса MyNewGameFilesFinder. Отличия в том, что во вспомогательных Методах поиск идет по
 * двум Строкам (Адресам), а основной метод (который используется в main) ищет файлы проекта в независимости где они
 * находятся (не как первый - искал только если файлы лежат в одной папке с названием проекта). */

 /* Класс осуществляет проверку на рабочем столе папки проекта
 * и если не находит ее, чекает скрытий файл на рабочем столе (если он есть) и проверяет работоспособность двух ссылок
 * на файлы проекта (текст файл с инфой и папка с фотками). Если эти ссылки не работают, начинает поиск на компе
 * двух этих файлов проекта. Если находит, записывает адреса файлов (2-х) в статические переменные и записывает их же
 * в скрытый текст файл на рабоем столе (если его нет, создает его) */

public class MyNewGameFilesFinder {
	
	/* заводим статитч. переменную для определения факта использования адреса папки с рабочего стола
	 * или переписанного после поиска */
	
	private static int k=0;
	
	/* заводим статическую переменную для подсчета количества вызовов Метода и регулировки глубины вложения */
	
	private static int j=0;
	
	/* заводим статическую переменную для установки глубины вложения */
	
	private static int h=5; // глубина вложения 5: С:/Stuff/Stuff/Stuff/Stuff
	
	/* это переменная для названия тестового файла, который создается, чтобы хранить адреса файлов */
	
	private static String v = "InfoFile.txt";
	
    /* заведем ArrahList <String> для записи туда всех найденых Путей к найденым файлам/папкам */
	
	private static ArrayList <String> findList = new ArrayList <String>();
	
	/* 1. вспомогательный Метод, принимающий 3 строки и проверяющий содержит ли первая строка вторую или трерью.
	 * Применяется в следующем Методе isContainList, т.е. данный метод не знает что сравнивать,
	 * он сравнивает только то, что ему передали. То что сравнивать, будет определять следующий метод,
	 * в котором и будет применяться данный метод */
	
	private static boolean newIsContainMethod (String a, String b, String c) {
		
		boolean k = false;
		
		if ( a.equals(b) || a.equals(c)) {  // ищем только Полное совпадение Имени
			
			k = true;
		}
				
		return k;
		
	} // конец метода newisContainMethod()
	
	/* 2. следующий метод принимает Директорию (массив файлов) и две строки, которые ищет в названиях файлов/папок директории
	 * если находится совпадение, путь к этому файлу/папке записывается в Список (статический).
	 * Т.е. Метод добавляет в Список адреса найденых файлов/папок из одной Директории, т.е. он не умеет углубляться,
	 * в отличии от следующего Метода, в котором данный метод и будет применяться */
	
	private static void newIsContainList (File[] a, String b, String c) {

		/* перебираем имена файлов/папок переданной директории (массива типа File) и сравниваем с искомой строкой,
		 * используя предидущий метод isContainMethod, если есть совпадение, пишем адрес этого файла/папки в список. */
		
		String kk;
		
		for (int i=0; i<a.length; i++) {
					
			if ( newIsContainMethod (a[i].getName(), b, c) ) {		
				
				kk = a[i].toString();
				
				/* адрес файла/папки, название которого совпадает с переданным словом,
				 *  записывается в Список */
				
				findList.add(kk); 
				
			} 
			
		} // конец нашего цикла for
		
	} // конец Метода newIsContainList()
	
	/* 3. Метод получает директорию и имя файла/папки и осуществляет их поиск в этой директории
	 * с заданной глубиной вложения, т.е. использует Рекурсию и формирует Список со всеми
	 * адресами найденых совпадеий. Просто формирует Список, а уже сам список будет применяться
	 * в следующем Методе, в которм из этого списка будут формироваться Статические переменные
	 * адресов файлов */
	
	private static void newInDirectorySearching (File[] arr, String bb, String cc) {
		
		j++; // при каждом вызове метода, j+1

		/* передаваемый Массив файлов помещаем в цикл, где к каждому элементу применяем
		 * метод listFiles(), т.е. раскладываем каждую Папку на ее содержимое и уже содержимое
		 * проверяем на соответствие искомому слову */
		
		for (int i=0; i<arr.length; i++) {
	
			/* в итоге мы имеем новый массив файлов (за каждую итерацию), содержащийся в
			 * каждой папке передаваемого Массива */
				
			File[] arra = arr[i].listFiles(); // массив всех элементов содержащихся в каждой папке
		
			if (arra != null) { // если это не файл и если это папка с открытим доступом
				
				/* пробегаемся по всем элементам директории (и папкам и файлам), если есть совпадение
				 * записяваем этот путь в лист и углубляемся дальше*/
				
				newIsContainList(arra, bb, cc);									
			}
			
			/* отсеиаем только Папки чтобы перейти на уровень глубже */
			
			File[] arra1 = arr[i].listFiles(File::isDirectory); // массив только Директорий, это для углубления
	
			/* если к папке имеется доступ, то мы применяем РЕКУРСИЮ, т.е. передаем эту директорию в наш метод
			 * аргументом и также ищем там нашу строку, как-бы погружаемся на уровень грубже */
			
			if (arra1 != null) { // если к папке имеется доступ
	
				if (j<h-1) { // условие для регулировки определенной глубины вложения
					
					newInDirectorySearching (arra1, bb, cc); // Рекурсивный вызов нашего Метода (самого себя)
					
					j--; // регулятор глубины вложния - надо разгадать тайну этого выражения) почему это работает?
				} 				
			}
		
		} // конец цикла for
		
	} // конец 3-го метода newInDirectorySearching
	
	/* 4. Метод, который формирует адреса моих двух файлов, нахорящихся в разный местах
	 * а не в папке Проекта. Применяется в main */
	
	public static void MyNewGameFilesAddress () {  
		
		/* создадим Объекты класса File с нужными нам адресами, а затем проверим их на существование, если все существуют
		 * значит присвоим эти адреса нашим статическим переменным fileAddres и addres, если нет то будем искать есть ли
		 * скрытый текстовый файл на рабочем столе (с адресами наших 2-х файлов)  - метод № 5 checkingTheAddresTextFile()
		 * и если его нет (или есть, но по этим адресам не те файлы или их нет) ищем файлы уже на всем компьютере,
		 * используя Предидущий метод № 3 inDirectorySearching() */

		File find_TextFile = new File (TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.textFileName); // текст файл
		File find_PhotoFolder = new File (TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator +
				TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1));  // фото-папка
	
		/* папка будет считаться существующей в 2-х случаях: если существуют текстовый файл и файл с фотками */
		
		/*В первом случае k=1, это когда файлы нашлись на Рабочем столе
		 * во втором случае k=2, это когда адреса файлов нашлись по адресам из скрытого текстового файла на рабочем столе
		 * это происходит в методе 5.checkingNewTheAddresTextFile(),
		 * в трерьем случае k=0, когда не нашлось ни на рабочем ни по адресам в файле и тогда начинается поиск на компьютере.
		 * Сейчас (15.04.25) я добавил еще 2 случая, когда на Рабочем нашелся только Текст Файл (k=3) и когда на Рабочем нашлись
		 * только Фотки (k=4) действий пока никаких */

		/*ВОТ ЗДЕСЬ НУЖНО БУДЕТ ВНЕСТИ ИЗМЕНЕНИЯ: у меня последующее if идет по выполнению сразу двух условий, а надо сделать по отдельности.
		 если например заменить && на || и уже в этом модуле сделать отдельные if для каждого случая.. и в каждом случае будет свое k и далее
		 в зависомости от значения k будут выполнения соответствующие меры.
		 Если нашли только текстовый файл, значит надо искать папку с фотками - сначало по ссылке в скрытом текст файле а потом уже и на
		 Git Hub, но если не нашли фотки, прога все равно может запуститься. Если нашли только фотки, то надо искать Текст Файл */
		
		if ( find_TextFile.exists()  && find_PhotoFolder.exists()     ) {
		
			TryStuff.fileAddres = TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.textFileName;
		
			TryStuff.addres = TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.photoFolderName;
			
			k=1; // метка, что и Фотки и Текст Файл есть
		
			/* если файл существует на рабочем столе, то все норм и мы ничего не делаем, адреса прописаны в TryStuff в 
			 * статических переменных */	
		}
		
		else if ( find_TextFile.exists() && !find_PhotoFolder.exists() ) {
			
			nesServiceClass.windowShow ("Текст Файл есть, Фоток нет");   // тестовое else if - работает
			
			/* здесь нужно организовать поиск Папки с Фотографиями на локальной машине и при нахождении этой папки, ее адрес нужн присвоить
			 * статической перменной TryStuff.addres (и возможно прописть в скрытый текст. файл) */
			
			k=3; // метка, что Фоток нет
		}
		
		else if ( find_PhotoFolder.exists() && !find_TextFile.exists() ) {
			
			nesServiceClass.windowShow("Фотки есть, а Текст Файла нет"); // тестовое else if - работает
			
			/* здесь нужно организовать поиск Текстового Файла на локальной машине и при его нахождении, присваиваем его адрес
			 * статической переменной TryStuff.fileAddres (и возможно прописать в скрытый текст. файл) */
			
			k=4; // метка, что Текст Файла нет
		}
		
		else { checkingNewTheAddresTextFile(); } // если не нашли на рабочем, чекаем скрытый текстовый файл на рабочем
		
		/* заведем переменную "p" для подсчета кол-ва найденных и записанных в переменные адресов. Их должно быть
		 * 2 и более. Если меньше 2, то заканчиваем прогу (MyGameSearcher.choice = 1;) */
		
		int p = 0;
		
		/////////////// вот блок поиска на компе и записи двух адресов в текстовый файл нужно дорабатывать /////////////
			
		if (k==0) { // начало моего нового иф (т.е. если не нашли ни на рабочем, ни в файле)
				
			//TryStuff.windowShowString("Щас вошли в блок поиска.");
				
			////////////////////////// Блок открытия Окна длительности процесса в отдельном Потоке ////////////////////

			SwingUtilities.invokeLater(() -> {
        	
				myWindow.procesWindow ("Внимание! Идет поиск...");
			});
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////       
					
			File[] roots = File.listRoots(); // составляем список жестких дисков
			
			/* проводим поиск по всему компу файла и папки с названиями "GamesFile.txt" (TryStuff.textFileName)
			 * и "GamesPhoto" (TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-2) - т.е.
			 * "GamesPhoto//" с удалением последних вдух символов "//" */
			
			//System.out.println ("Let's check TryStuff.photo: " + TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1)); // GamesPhoto
			//System.out.println ("Let's check TryStuff.textFileName: " + TryStuff.textFileName);
			
			newInDirectorySearching (roots, TryStuff.textFileName, TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1));
			
			//TryStuff.windowShowString("До начало просеивания размер Списка - " + findList.size());
			
			/* цикл отсеивания из Списка адресов, ссылающихся на Ресайкл Бины */
			
			String[] myTempArray = findList.toArray(new String[0]); // переводим Список в Массив, а Список очищаем
			findList.clear(); // очистка Списка
			
			for (int i=0; i<myTempArray.length; i++) {
				
				if ( myTempArray[i].contains("$Recycle.Bin") || myTempArray[i].contains("$RECYCLE.BIN") ) {
					
					//TryStuff.windowShowString("Этот адрес не пишем - " + myTempArray[i]);
					
				} else {
					
					//TryStuff.windowShowString("Этот адрес оставляем - " + myTempArray[i]);
					findList.add(myTempArray[i]);
				}
				
			}
		
			/* после этого блока в адресе Папки с фотками еще нет черты "\\" */
			
			//TryStuff.windowShowString("После просеивания размер Списка - " + findList.size());
			
			/* формируем Массив из строк с адресами найденных файлов/папок, т.е. здесь будет адреса папки с фотками
			* и адреса текстового файла проекта */
			
			String[] myArray = findList.toArray(new String[0]);
		
			/////////////////// Блок закрытия окна длительности процесса после того как поиск закончился ////////////////////

			SwingUtilities.invokeLater(() -> {
          
				for (Frame frame : Frame.getFrames()) {

        			frame.dispose();
				}

			});
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////        
		
			nesServiceClass.windowShow (myArray); // пока попробуем вывести просто на экран что нашлось
			
			/* заводим два списка, в которые далее в цикле будем записывать адреса найденых текстовых файлов
			 * и адреса папок с фотками (если их на компе будет найдено несколько) чтобы в дальнейшем выбирать
			 * из них наиболее свежие по дате */
			
			List <String> adrFl = new ArrayList <String> (); // под текст файлы
			List <String> adrPh = new ArrayList <String> (); // под папки фоток
			
			/* далее следует цикл проверки всех найденных адресов на существование файлов на которые они указывают */
			
			for (int i=0; i<myArray.length; i++) {
			
				/* формируем объекты класса File по адресам, совпадений записанных в наш Список (т.е. Адреса:
				 * папки и в ней текстового файла и папки с фотками) и если все три данных объекта существуют,
				 * то присваиваем эти адреса нашим статическим переменным fileAddres и addres */

				File findTextFile = new File (myArray[i]);
				File findPhotoFolder = new File (myArray[i]); // адрес еще без черты "\\"
			
				/* формируем адрес текстового файла */
				
				if (   findTextFile.exists()  &&  myArray[i].contains(TryStuff.textFileName)  ) {
				
					adrFl.add(myArray[i]); // собираем адреса всех найденых файлов Проекта
					
					/* вот тут нужно применить метод по вычислению наиболее "свежего" адреса файла из всех
					 * файлов на которые указывают все собранные в списке адреса и далее записать
					 * этот адрес в TryStuff.fileAddres */
					
					/* вот здесь и формируем Статические переменные, содержащие адреса наших найденных файлов */
				
					//TryStuff.fileAddres = myArray[i];
					
					//TryStuff.windowShowString(" В TryStuff.fileAddres записываем " + myArray[i]);
					
					p++;
					continue;					
				}
				
				/* формируем адрес Папки с фотографиями */
				
				else if (  findPhotoFolder.exists()  &&  myArray[i].contains(TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1))  ) {

					adrPh.add(myArray[i]); // собирае адреса всех папок с фотками проекта
					
					/* вот тут нужно применить метод по вычислению наиболее "свежего" адреса папки из всех
					 * папок на которые указывают все собранные в списке адреса и далее записать
					 * этот адрес в TryStuff.addres */
					
					//TryStuff.addres = myArray[i] + "\\"; // вот здесь и появляется наш Слеш "\\"
					
					//TryStuff.windowShowString(" В TryStuff.addres записываем " + TryStuff.addres);
					
					p++;
					continue;		
				}
			
			} // конец нового цикла for
	
		///////////////////////////////////////////////////////////////////////////////////////////////////////
			
			/* блок присваивания переменной TryStuff.fileAddres значения из списка adrFl с адресом наиболее
			 * свежего по дате изменения файла */
			
			try {
				
				String newestFilePath = findNewestFile(adrFl);
				
				if (newestFilePath != null) {
					
					//System.out.println("The most recently modified file is: " + newestFilePath);
					
					/*вот здесь и присваиваем нашей переменной значиние адреса с самым новым файлом */
					
					TryStuff.fileAddres = newestFilePath;
					
				} else {
					System.out.println("No valid files found.");
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			/* блок присваивания переменной TryStuff.addres значения из списка adrFl с адресом наиболее
			 * свежего по дате изменения файла */
			
			try {
				
				String newestFolderPath = findNewestFolder(adrPh);
				
				if (newestFolderPath != null) {
					
					//System.out.println("The most recently modified file is: " + newestFilePath);
					
					/*вот здесь и присваиваем нашей переменной значиние адреса с самой новой папкой */
					
					TryStuff.addres = newestFolderPath + "\\";
					
				} else {
					System.out.println("No valid folders found.");
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		///////////////////////////////////////////////////////////////////////////////////////////////////////
			
			nesServiceClass.windowShow ("Блок формирования переменных окончен");
			nesServiceClass.windowShow ("TryStuff.fileAddres - " + TryStuff.fileAddres);
			nesServiceClass.windowShow ("TryStuff.addres - " + TryStuff.addres);
			
			///////////////////// блок записи найденных адресов в текстовый файл на рабочем столе ///////////////////
					
			/*проверим наличие на рабочем столе текстового файла и при отсутствии заведм его 
			 *Создаем объект класса File со следующим Конструктором */
				
			/* метод cerateNewFile() может выбрасывать Исключения, соответственно его нельзя употреблять без конструкции
			* try-catch */
					
			File deskTop = new File (TryStuff.desktopPath);    // директория - рабочий стол
				
			File myTextFile =  new File (deskTop, v); //  текстовый файл с адресами на рабочем столе
				
			try {
						
				// если такой папки нет, создаем ее, хотя рабочий стол должен быть всегда
						
				if (!deskTop.exists()) {
					deskTop.mkdirs();								
				}
						
				/* В нашей Директории (на рабочем столе) создадим наш текстовый скрытый файл, а если он уже есть
				* то перезапишем его */
							
				String x1 = TryStuff.desktopPath + File.separator + v; // путь к файлу
							
				Path hiddenFile = Paths.get(x1);
							
				/* создаем скрытый текстовый файл, если его еще нет на Рабочем Столе */
							
				if (Files.notExists(hiddenFile)) { 
								
					Files.createFile((hiddenFile)); // создаем файл
					Files.setAttribute(hiddenFile, "dos:hidden", true); // устанавливаем его свойства
								
					//TryStuff.windowShowString("Hidden file was created.");
								
					try (BufferedWriter writer = new BufferedWriter (new FileWriter(x1, true))) {
									
						writer.write (TryStuff.fileAddres);
						writer.newLine();
						writer.write (TryStuff.addres+"\n");
									
						} catch (IOException e) {
							e.printStackTrace();
						}
								
					/* если наш скрытый файл уже существует, то перезаписываем туда наши адреса */
								
				} else {
								
					Files.setAttribute(hiddenFile, "dos:hidden", false); // делаем его НЕскрытым, пишем в него и Скрываем
								
					try (BufferedWriter writer = new BufferedWriter (new FileWriter (x1))) {
								
						writer.write (TryStuff.fileAddres);
						writer.newLine();
						writer.write (TryStuff.addres+"\n");
								
						Files.setAttribute(hiddenFile, "dos:hidden", true); // делаем файл Скрытым
								
					} catch (IOException e) {
									
						e.printStackTrace();
					}
								
					nesServiceClass.windowShow ("Наш скрытый текст файл перезаписан.");
				}
					
			} catch (IOException e) {
						
				System.err.println ("Error creating: " + e.getMessage());
			}
		
			if (p < 2) { // если не нашлось ничего или нашелся только один адрес заканчиваем прогу
							
				nesServiceClass.windowShow ("На этом компе ничего нет, извините.");

				MyGameSearcher.choice = 1; // для того чтобы основной цикл не начинался если папка с документами не нашлась
			}
						
		} // конец основного if (k==0)
	
	} // конец Метода 4. MyNewGameFilesAddress
	
	/* 5. Метод, который будет проверять наличие на рабочем столе (или еще где-то) текстового документа, с 2-мя нужными мне адресами,
	 * и если документа нет, то ничего происходить не должно, если же файл существует, то надо считать из него адреса файлов
	 * и записать в статические переменные. Используется в предидущем Методе № 4 */
	
	private static void checkingNewTheAddresTextFile () {
		
		//TryStuff.windowShowString("Запущен метод по чеку скрытого файла");
		
		/*проверим наличие на рабочем столе скрытого текстового файла (для записи в него адресов нужных нам файлов)
		 *и при отсутствии заведм его 
		 *Создаем объект класса File со следующим Конструктором */
		
		/* метод cerateNewFile() может выбрасывать Исключения, соответственно его нельзя употреблять без конструкции
		 * try-catch */
		
		File deskTop = new File (TryStuff.desktopPath);    // директория - рабочий стол
		
		File myTextFile =  new File (deskTop, v); //  скрытый текстовый файл с адресами на рабочем столе
		
		if (myTextFile.exists()) { // если файл существует
					
			/* русский язык в текстовом файле shit не читает, викидывает исключение */
	
			try {
	
				/* класс Path, это класс пакета java.nio.file. Поэтому создаем Объект класса Path и
				 * используя метод get вводим адрес расположения нашего текстового файла */
		
				Path path = Paths.get(TryStuff.desktopPath+ File.separator + v);
		
				/* используем класс File и его метод readAllLines() чтобы прочитать текстовый файл
				 * и сформировать из этих данных список Строк */
		
				List <String> stuff = Files.readAllLines(path);
				
				//TryStuff.windowShowString("Размер считываемого файла - " + stuff.size());
				
				String[] a23 = stuff.toArray(new String[0]); // список переводим в Массив
				
				File findFile1 = new File (a23[0]); // формируем объект класса File с адресом из нашего массива найденых файлов
				File findFile2 = new File (a23[1]);

				//if (findFile1.exists()) { System.out.println(a23[0] + " - that's working."); }
				//if (findFile2.exists()) { System.out.println(a23[1] + " - that's working."); }
				
				/* считываем адреса из скрытого текстового файла на рабочем столе */
				
				if (stuff.size()==2) {
					
					if (   findFile1.exists()   && a23[0].contains(TryStuff.textFileName)
												&& findFile2.exists()
							                    && a23[1].contains(TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1)) ) {
				
						/* записываем адреса в статические переменные и k = 1 - это увидит Метод № 4 и не будет начинать поиск */
				
						TryStuff.fileAddres = a23[0];
						TryStuff.addres = a23[1];
				
						//System.out.println("Fist version");
						
						k=2;						
					}
					
					else if (   findFile1.exists()   && a23[0].contains(TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1))
							                         && findFile2.exists()
							                         && a23[1].contains(TryStuff.textFileName)) {

						TryStuff.addres = a23[0];
						TryStuff.fileAddres = a23[1];
						
						//System.out.println("Second version");
					
						k = 2;
						
					} else {

						System.out.println("Nothing is found and TryStuff.fileAddres is " + TryStuff.fileAddres);
											
						k = 0;
					}
				
				} else { 
					
					nesServiceClass.windowShow ("Количество адресов в скрытом файле не равно двум");
					
					k = 0;					
				}
				
			} catch (IOException e) {
		
				nesServiceClass.windowShow ("something went wrong and the error is " + e.getMessage());
			}

		} // конец главного if (myTextFile.exists())
						
	} // конец Метода № 5. checkingNewTheAddresTextFile ()
	
	/* 6. Вот этот метод будет принимать список строк (ArrayList) т.е. адреса файлов, потом чекать атрибуты
	 * этих файлов и выбирать наиболее "свежий" файл, т.е. файл с самой свежей датой последней модификации
	 * и будет возвращать эту Строку (адрес). Применяться будте в методе №4 MyNewGameFilesAddress () при
	 * формировании переменной TryStuff.fileAddres. Используется в Методе № 4 */
	
	public static String findNewestFile(List <String> filePaths) throws IOException {
		
		Path newestFilePath = null;
		
		long newestModifiedTime = Long.MIN_VALUE;

		for (String filePath : filePaths) {
			
			Path path = Paths.get(filePath);

			// Check if the path exists and is a file
			
			if (Files.exists(path) && Files.isRegularFile(path)) {
				
				BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
				long lastModifiedTime = attrs.lastModifiedTime().toMillis();

				// Update the newest file if this one is more recent
				if (lastModifiedTime > newestModifiedTime) {
					newestModifiedTime = lastModifiedTime;
					newestFilePath = path;
				}
			}
		}

		return (newestFilePath != null) ? newestFilePath.toString() : null;
		
	} // конец Метода № 6
	
	/* 7. Вот этот метод такой же как предидущий только оперирует адресами Папок. Будет принимать список строк
	 * (ArrayList) т.е. адреса папок, потом чекать атрибуты этих папок и выбирать наиболее "свежую" папку, т.е.
	 * папку с самой свежей датой последней модификации и будет возвращать эту Строку (адрес).
	 *  Применяться будте в методе №4 MyNewGameFilesAddress () при формировании переменной TryStuff.addres.
	 *  Используется в Методе № 4 */
	
	public static String findNewestFolder(List <String> folderPaths) throws IOException {
		
		Path newestFilePath = null;
		
		long newestModifiedTime = Long.MIN_VALUE;

		for (String filePath : folderPaths) {
			
			Path path = Paths.get(filePath);

			// Check if the path exists and is a file
			
			if (Files.exists(path) && Files.isDirectory(path)) {
				
				BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
				long lastModifiedTime = attrs.lastModifiedTime().toMillis();

				// Update the newest file if this one is more recent
				if (lastModifiedTime > newestModifiedTime) {
					newestModifiedTime = lastModifiedTime;
					newestFilePath = path;
				}
			}
		}

		return (newestFilePath != null) ? newestFilePath.toString() : null;
		
	} // конец Метода № 7 

} // конец Класса
