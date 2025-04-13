
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import java.awt.Frame;

/* мой Класс, осуществляющий проверку на рабочем столе папки проекта и если не находит ее, то ищет ее
 * на компьютере. Если находит, записывает адреса нужных папок (2-х) в статические переменные */

/* это Класс моей первой концепции поиска файлов на Компе. Т.е. он ищет Папку проекта и в ней уже
 * смотрит наличие файлов Проекта. Сейчас я пользуюсь второй концепцией, это класс MyNewGameFilesFinder
 * и поиск идет по файлам (текстовый файл и папка с фотками) */

public class MyGameFilesFinder {
	
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
	
	/* 1. вспомогательный Метод, принимающий 2 строки и проверяющий содержит ли первая строка вторую,.
	 * Применяется в следующем Методе isContainList, т.е. данный метод не знает что ставнивать,
	 * он сравнивает только то, что ему передали. То что сравнивать, будет определять следующий метод,
	 * в котором и будет применяться данный метод */
	
	private static boolean isContainMethod (String a, String b) {
		
		boolean k = false;
		
		if (a.equals(b)) {  // ищем только Полное совпадение Имени
			
			k = true;
		}
				
		return k;
		
	} // конец метода isContainMethod()

	/* 2. следующий метод принимает Директорию (массив файлов) и Строку, которую ищет в названиях файлов/папок директории
	 * если находится совпадение, путь к этому файлу/папке записывается в Список (статический).
	 * Т.е. Метод добавляет в Список адреса найденых файлов/папок из одной Директории, т.е. он не умеет углубляться,
	 * в отличии от следующего Метода, в котором данный метод и будет применяться */
	
	private static void isContainList (File[] a, String b) {

		/* перебираем имена файлов/папок переданной директории (массива типа File) и сравниваем с искомой строкой,
		 * используя предидущий метод isContainMethod, если есть совпадение, пишем адрес этого файла/папки в список. */
		
		String kk;
		
		for (int i=0; i<a.length; i++) {
					
			if ( isContainMethod (a[i].getName(), b) ) {		
				
				kk = a[i].toString();
				
				/* адрес файла/папки, название которого совпадает с переданным словом,
				 *  записывается в Список */
				
				findList.add(kk); 
				
			} 
			
		} // конец нашего цикла for
		
	} // конец Метода isContainList()
	
	/* 3. Метод получает директорию и имя файла/папки и осуществляет их поиск в этой директории
	 * с заданной глубиной вложения, т.е. использует Рекурсию и формирует Список со всеми
	 * адресами найденых совпадеий. Просто формирует Список, а уже сам список будет применяться
	 * в следующем Методе, в которм из этого списка будут формироваться Статические переменные
	 * адресов файлов */
	
	private static void inDirectorySearching (File[] arr, String bb) {
		
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
				
				isContainList(arra, bb);
										
			}
			
			/* отсеиаем только Папки чтобы перейти на уровень глубже */
			
			File[] arra1 = arr[i].listFiles(File::isDirectory); // массив только Директорий, это для углубления
	
			/* если к папке имеется доступ, то мы применяем Рекурсию, т.е. передаем эту директорию в наш метод
			 * аргументом и также ищем там нашу строку, как-бы погружаемся на уровень грубже*/
			
			if (arra1 != null) { // если к папке имеется доступ
	
				if (j<h-1) { // условие для регулировки определенной глубины вложения
					
					inDirectorySearching (arra1, bb); // Рекурсивный вызов нашего Метода (самого себя)
					
					j--; // регулятор глубины вложния - надо разгадать тайну этого выражения) почему это работает?
				} 				
			}
		
		} // конец цикла for
		
	} // конец 3-го метода inDirectorySearching
	
	/* 4. Метод который непосредственно применяется в main и который в случае не нахождения папки на рабочем столе будет
	 * осуществлять поиск по всему компу и перезаписывать Статические Переменные с адресом файлов (плюс еще записывает
	 * эти переменные в текстовый файл, чтобы при повторном запуске проги, переменные считывались уже из него,
	 *  т.е. он ничего не возвращает, а определяет то, что будет содержать Статические Переменные адресов файлов */
	
	public static void MyGameFilesAddress () { // аргументом передаем Название Папки проекта  
			
			/* создадим Объекты класса File с нужными нам адресами, а затем проверим их на существование, если все существуют
			 * значит присвоим эти адреса нашим статическим переменным fileAddres и addres, если нет то будем искать есть ли
			 * скрытый текстовый файл на рабочем столе (с адресами наших 2-х файлов)  - метод № 5 checkingTheAddresTextFile()
			 * и если его нет (или есть, но по этим адресам не те файлы или их нет) ищем файлы уже на всем компьютере,
			 * используя Предидущий метод № 3 inDirectorySearching() */
		
		/* myFolder - это папка MyGamesSearcher - изначально на Раб Столе, а вообще на компе в целом */
		/* find_TextFile -  мой Текстовый файл */
		/* find_PhotoFolder - это моя папка с Фотографиями проекта */
		
			File myFolder = new File (TryStuff.desktopPath + File.separator + TryStuff.projectFolderName);
			File find_TextFile = new File (TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.textFileName);
			File find_PhotoFolder = new File (TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1));
		
			//TryStuff.windowShowString(TryStuff.desktopPath + File.separator + a);
		
			/* папка будет считаться существующей в 3-х случаях: если она существует и в ней существуют текстовый файл и файл с фотками.
			 * В дальнейшем можно тут что-то исправить */
		
			if (  myFolder.exists()   &&   find_TextFile.exists()  && find_PhotoFolder.exists()     ) {
			
				//	TryStuff.windowShowString("Папака существует на рабочем столе.");
			
				TryStuff.fileAddres = TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.textFileName;
			
				TryStuff.addres = TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.photoFolderName;
				
				k=2;
			
				/* если файл существует на рабочем столе, то все норм и мы ничего не делаем, адреса прописаны в TryStuff в 
				 * статических переменных */
			
			} else { checkingTheAddresTextFile(); } // если не нашли на рабочем, чекаем скрытый текстовый файл на рабочем
			
				//	TryStuff.windowShowString("На рабочем столе ничего не найдено, сейчас поищем на Компьютере");
			
				if (k==0) { // начало моего нового иф (т.е. если не нашли ни на рабочем, ни в файле)
					
				////////////////////////// Блок открытия Окна длительности процесса в отдельном Потоке ////////////////////

				SwingUtilities.invokeLater(() -> {
	        	
					myWindow.procesWindow ("Внимание! Идет поиск...");
				});
				/////////////////////////////////////////////////////////////////////////////////////////////////////////       
					
			
				File[] roots = File.listRoots(); // составляем список жестких дисков
			
				inDirectorySearching (roots, TryStuff.projectFolderName); // проводим поиск по всему компу Папки с названием аналогичым Переданному в Метод
			
				String[] myArray = findList.toArray(new String[0]); // формируем Массив из строк с адресами найденных файлов/папок
			
				/////////////////// Блок закрытия окна длительности процесса после того как поиск закончился ////////////////////

				SwingUtilities.invokeLater(() -> {
	          
					for (Frame frame : Frame.getFrames()) {

	        				frame.dispose();
					}

				});
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////        
			
				nesServiceClass.windowShow(myArray); // пока попробуем вывести просто на экран что нашлось
			
				int p = 0; // счетчик для подсчета количества проходов за которые ничего не нашлось
			
				for (int i=0; i<myArray.length; i++) {
				
					/* формируем объекты класса File по адресам, совпадений записанных в наш Список (т.е. Адреса:
					 * папки и в ней текстового файла и папки с фотками) и если все три данных объекта существуют,
					 * то присваиваем эти адреса нашим статическим переменным fileAddres и addres */
				
					File findFolder = new File (myArray[i]); // формируем объект класса File с адресом из нашего массива найденых файлов
					File findTextFile = new File (myArray[i] + File.separator + TryStuff.textFileName);
					File findPhotoFolder = new File (myArray[i] + File.separator + TryStuff.photoFolderName.substring(0, TryStuff.photoFolderName.length()-1));
				
					if (   findFolder.exists()   &&   findTextFile.exists()  && findPhotoFolder.exists()   ) {
					
						/* вот здесь и формируем Статические переменные, содержащие адреса наших найденных файлов */
					
						TryStuff.fileAddres = myArray[i] + File.separator + TryStuff.textFileName;
					
						TryStuff.addres = myArray[i] + File.separator + TryStuff.photoFolderName;
					
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
								}
						
							} catch (IOException e) {
							
								System.err.println ("Error creating: " + e.getMessage());
							}
			
					}
				
					else { p++; }
				
				}
		
				if (p == myArray.length) { // если в каждый проход ничего не было найдено (p == myArray.length) заканчиваем прогу
				
					nesServiceClass.windowShow ("На этом компе ничего нет, извините.");
					
		///////////////* здесь можно сделать возможность включить интернет *//////////////
				
					MyGameSearcher.choice = 1; // для того чтобы основной цикл не начинался если папка с документами не нашлась
				}
				
				} // конец моего нового иф
		
	} // конец 4-го Метода MyGameFilesAddress
	
	/* 5. Метод, который будет проверять наличие на рабочем столе (или еще где-то) текстового документа, с 2-мя нужными мне адресами,
	 * и если документа нет, то ничего происходить не должно, если же файл существует, то надо считать из него адреса файлов
	 * и записать в статические переменные*/
	
	private static void checkingTheAddresTextFile () {
		
		/*проверим наличие на рабочем столе текстового файла и при отсутствии заведм его 
		 *Создаем объект класса File со следующим Конструктором */
		
		/* метод cerateNewFile() может выбрасывать Исключения, соответственно его нельзя употреблять без конструкции
		 * try-catch */
		
		File deskTop = new File (TryStuff.desktopPath);    // директория - рабочий стол
		
		File myTextFile =  new File (deskTop, v); //  текстовый файл с адресами на рабочем столе
		
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
				
				if (stuff.size()==2) {
		
					String[] a23 = stuff.toArray(new String[0]); // список переводим в Массив
					
					File findFolder = new File (a23[0]); // формируем объект класса File с адресом из нашего массива найденых файлов
					File findTextFile = new File (a23[1]);

				
					if (   findFolder.exists()   &&   findTextFile.exists()  ) {
				
						/* записываем адреса в статические переменные и k = 1 - это увидит Метод № 4 и не будет начинать поиск */
				
						TryStuff.fileAddres = a23[0];
						TryStuff.addres = a23[1];
				
						k=1;
						
					} else {
						
						//TryStuff.windowShowString("По адресам в скрытом файле ничего не нашлось");
					
						k = 0;
					}
				
				} else { 
					
					//TryStuff.windowShowString("Количество адресов в скрытом файле не равно двум");
					
					k = 0;
					
				}
				
			} catch (IOException e) {
		
				nesServiceClass.windowShow ("something went wrong and the error is " + e.getMessage());
			}

		} // конец главного if (myTextFile.exists())
						
	} // конец Метода № 5 checkingTheAddresTextFile ()
	
} // конец всего Класса MyGameFilesFinder
