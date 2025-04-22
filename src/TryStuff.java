
import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

/* Класс в котором расположено описание Полей Игры, т.е. Поля и Конструкторы по формированию
 * наший Объектов (Игр) а также Методы по обработке наших Объектов. Это и Поиск и Сортировка,
 * вывод на экран Фотографий из папок и из Интернета. Также содержит статические переменные
 * с агресами файлов проекта. Нужно будет соствить подроброе содержание этого Класса */

public class TryStuff {
	
	//////////////////////////// Данные (Поля) моего Класса (10 штук) /////////////////////////
	
	// 7 основных 
	
	private String name;
	private String creator;
	private String mapper;
	private String year;
	private String comment;
	private int    amount;
		
	private String [] pics;
		
	// 3 внутренних (использую только в Конструкторах)
		
	private String addr1  = "1. General/1.jpg";   // Файл Фоток по умолчанию
	private String addr2  = "1. General/2.jpg";
	private String addr3  = "1. General/3.jpg";
		
	/////////////////// Блок формирования Адресов и названий Файлов/Папок всего Проекта //////////////
		
	/* переменная deskpotPath это адрес, ссылающийся на Рабочий стол любого компьютера, на который мы
	* будем помещать папку с нашим приложением */
		
	/* я собрал все статические переменные здесь, т.е. если что-то и менять, то здесь.
	 * В других методах значение этих переменных может меняться в зависимости от результатов
	 * поиска, но все начальные данные присваиваются здесь */
		
	static String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
		
	/* заведем переменную под название папки Проекта */
		
	static String projectFolderName = "MyGameSearcher";
		
	/* переменная с именем Текстового файла с данными Проекта */
		
	static String textFileName = "GamesFile.txt";
		
	/* переменная с именем папки с Фотографиями Проекта */
		
	static String photoFolderName = "GamesPhoto\\";
		
	/* переменная с адресом расположения Текстовго файла Проекта */
		
	static String fileAddres = desktopPath + File.separator + projectFolderName + File.separator + textFileName;
		
	/* переменная с адресом расположения Папки с Фотографиями Проекта */
		
	static String addres = desktopPath + File.separator + projectFolderName + File.separator + photoFolderName;
		
	/* переменная для адреса фоток хранящихся на Git Hub */
		
	static String webAddres = "https://raw.githubusercontent.com/Borrra/GamesPhoto/main/";
		
	/* переменная для адреса текстового файла хранящегося на Git Hub */
		
	static String webFileAddres = "https://raw.githubusercontent.com/Borrra/GamesFile/main/GamesFile.txt";
		
	/* переменная для адреса Интернет сайта, по которому проверяем наличие Интернета */
		
	static String inetChecking = "http://www.google.com";
		
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	static String j2 = "all Games";  // переменная для отображения того, по чему ищем для окна выбора Мышкой
	static String j3 = " ";          // переменная для отображения того, по чему ищем второй раз для окна выбора Мышкой
	static int    m1 = 0;            // для отображения числа отсортированных элементов в Окне выбора Мышью
		
	static String j;   // переменная для отображения того, по чему ищем
	static String k;   // для отображения слова первого уровня поиска в шапке окна
		
	static String k1 = "";   // для хранения Названия Издателя, Маппера или Года выбранного Мышью из Списка в Окне
		
	static int t=0;    // для отображения уровня сортировки
	static int m=0;    // для запоминания номера Игры выбранной по коду *_ _ _, т.е. это Номер Игры показываемой Окном - это важно!
		
	static int t1=0;   // для отслеживания факта Просмотра списка Издателей, Мапперов или Годов, для посдед. выбора по коду *_ _ 		
	static int z1=0;   // для различия списка чего хотим выбирать кодом *_ _ - это Издатели, Мапперы или Годы

	static int z4=0;   // для отслеживания факта выбора Игры мышью (если выбрали Игру из списка мышью - z4=1 (это в Методе №20)
		
		
	//////////////////////////// Конструкторы моего Класса (6 штук) /////////////////////////
		
	// 1. конструктор, если вводишь только Название (+ 3 дефолтных фотки)
		
	public TryStuff (String name) {  
			
		this.name = name;
		this.creator = "no name";
		this.mapper = "unknown";
		this.year = "19**";
		this.comment = "stay sharp";
		this.amount = 3;
		this.pics = new String [] {addres + addr1, addres + addr2, addres + addr3};
	}
		
	// 2. конструктор, если вводишь Название и Издателя (+ 3 дефолтных фотки)
		
	public TryStuff (String name, String creator) {  
			
		this.name = name;
		this.creator = creator;
		this.mapper = "unknown";
		this.year = "19**";
		this.comment = "stay sharp";
		this.amount = 3;
		this.pics = new String [] {addres + addr1, addres + addr2, addres + addr3};
	}
		
	// 3. конструктор, если вводишь Название, Издателя и Год (+ 3 дефолтных фотки)
		
	public TryStuff (String name, String creator, String year) {  
							
		this.name = name;
		this.creator = creator;
		this.mapper = "unknown";
		this.year = year;
		this.comment = "stay sharp";
		this.amount = 3;
		this.pics = new String [] {addres + addr1, addres + addr2, addres + addr3};
	}
				
	// 4. конструктор, если вводишь Название, Издателя, Маппер и Год (+ 3 дефолтных фотки)
		
	public TryStuff (String name, String creator, String mapper, String year) {  
							
		this.name = name;
		this.creator = creator;
		this.mapper = mapper;
		this.year = year;
		this.comment = "stay sharp";
		this.amount = 3;
		this.pics = new String [] {addres + addr1, addres + addr2, addres + addr3};
	}

	// 5. конструктор при вводе всех полей (+ 3 дефолтных фотки)
		
	public TryStuff (String name, String creator, String mapper,  
					 String year, String comment) {
		this.name = name;
		this.creator = creator;
		this.mapper = mapper;
		this.year = year;
		this.comment = comment;
		this.amount = 3;
		this.pics = new String [] {addres + addr1, addres + addr2, addres + addr3};
	}
		
	/* 6. Конструктор для записи массива фоток целиком (1 и более фоток), этот Конструктор помог избавитья от большого кол-ва
	 * конструкторов отличающихся только кол-ом фотографий. Т.е. теперь можо записывать в конструктор бесконечное кол-во
	 * фотографий одной игры. Сколько фоток есть в считываемом файле, столько и запишем */
		
	public TryStuff (String name, String creator, String mapper,  
				 	 String year, String comment, String [] pic) { // в конструктор передаем сразу массив Строк
		this.name = name;
		this.creator = creator;
		this.mapper = mapper;
		this.year = year;
		this.comment = comment;
			
		if (pic.length==1) { // если 1 фотка кастомная
				
			this.amount = 3;
			this.pics = new String [] {pic[0], addres + addr2, addres + addr3};		
		}
			
		else if (pic.length==2) { // если 2 фотки кастомные
				
			this.amount = 3;
			this.pics = new String [] {pic[0], pic[1], addres + addr3};		
		}
			
		else { // если 3 и более фотки кастомные
				
			this.amount = pic.length; // соответственно кол-во фоток определяется размером Массива
			this.pics = pic; // записываем в Поле pics массив адресов фоток целиком
		}
	}

///////////////////////////// Методы моего Класса (14 штук) ///////////////////////////
		
	/* 7 геттеров, 2 поиска, 4 вывода фоток на экран, 1 не используется */
	
	// 7 getters - по полям ("name", "year", "getAmount" и массив Адресов фоток)
		
	// 1.1 getter of "name" field (метод по получению Данных поля "name")
		
	public String getName() {
		return name;
	}
		
	// 1.2 getter of "name" field (метод по получению Данных поля "name")
		
	public String getCreator() {
		return creator;
	}
		
	// 1.3 getter of "name" field (метод по получению Данных поля "name")
		
	public String getMapper() {
		return mapper;
	}
		
	// 1.4 getter of "year" field - используется только в MyComp (для сортировки по году)
		
	public String getYear() {
		return year;
	}
		
	// 1.5 getter of "year" field - используется только в MyComp (для сортировки по году)
		
	public String getComment() {
		return comment;
	}

	// 1.6 getter of "amount" field (количество Фотографий данной Игры)
		
	public static int getAmount (List <TryStuff> list, int a) {

		int s;
					
		s = list.get(a).amount;
					
		return s;
	}
		
	/* 1.7 метод который будет возвращать массив с адресами фоток данной Игры */
		
	public static String[] gamePics (List <TryStuff> list, int a) {
			
		String[] s;
			
		s = list.get(a).pics;
			
		return s;
			
	}

	/* 01. статический Метод по созданию Массива Объектов Класса TryStuff
		   сейчас его уже НЕ ИСПОЛЬЗУЮ, беру данные из Файла */
		
	public static TryStuff[] createMyArray() {
			
		TryStuff [] myGames = {
			
			new TryStuff ("Addams Family 1", "Ocean", "MMC-1", "1991", "1 in 1"),
			
			new TryStuff ("Addams Family 2", "Ocean", "MMC-1", "1991", "1 in 1")
		};
			
		return myGames;
	}	
		
///////////////// 2 Метода (2.1 и 2.2) метода по Сортировке Списка Объектов ////////////////////////////
		
	/* 2.1 это Метод собирает совпдения в список и возврашать его */
		
	public static List <TryStuff> sortingListObjedts (List <TryStuff> arr) {
			
		int w = 0; // переменная для отслеживания того, какой список возвращаем
			
		/* создадим Список, который и будет возвращать Метод */
			
		List <TryStuff> objectList = new ArrayList <TryStuff> ();
			
		t++; // вызываем этот метод и t+1 сразу - для отображения этапа Сортировки

		String a;    // переменная под вводимое слово
				
	////////////// вводим слово с клавы: /////////////////
					
		// если это второй этап подсортировки, то об этом будет сказано
					
		if (t==2) {
			a = JOptionPane.showInputDialog(null, +t+ " Stage of sorting (out of " + j + ")",
					"Input Window", JOptionPane.QUESTION_MESSAGE);  // вводим слово в окно
		}
					
		// это для первого этапа подсортировки
					
		else {
			a = JOptionPane.showInputDialog(null, +t+ " Stage of sorting",
					"Input Window", JOptionPane.QUESTION_MESSAGE);  // вводим слово в окно										
		}

		j = a;  // запысываем то, что ввели в Статич. Переменную
					
		///////////////////// сортировка по полному совпадению по Полям /////////////////////////////
					
		// проходим по списку, и собираем в отдельный список Объекты, которые соответствуют условиям
					
		for (int i = 0; i<arr.size(); i++) {
				
			if ((a.equals(arr.get(i).creator))||(a.equals(arr.get(i).mapper))||(a.equals(arr.get(i).year))) {
					
				objectList.add(arr.get(i)); // добавляем Объект с совпадением в наш Список
					
				w++; 
			}				
		}

		if (w>0) {
			
			return objectList;
			
		};	// если что-то нашлось - возвращаем этот список
			
	/////////////////////////////////////////////////////////////////////////////////////////////////////
				
	/////////////////////////////////// Блок поиска непосредственно в Именах ////////////////////////////
					
		if (a.length()>0) {
						
			char n = a.charAt(0); // n - первый символ введенного слова
		
		///////////////////// блок для сортировки по Одной введеной Букве ////////////////////////////
			
			/* если введенное слово - это Один символ и это Буква, то делаем эту букву Заглавной
			 * (даже если она и была заглавной) и отсортировываем объекты, названия которых
			 * начинается на эту Букву */
						
			if ((a.length()==1)&&(Character.isAlphabetic(n))) {
							
				char n1 = Character.toUpperCase(n);
					
				a = String.valueOf(n1);

				for (int i = 0; i<arr.size(); i++) {

					if (a.equals(String.valueOf(arr.get(i).name.charAt(0)))) {

						objectList.add(arr.get(i)); // добавляем Объект с совпадением в наш Список
							
						w++;
					}				
				}
					
				if (w>0) {

					return objectList;
					
				};	// если что-то нашлось - возвращаем этот список
				
	/////////////////////////////////////////////////////////////////////////////////////////////////////
				
    /////////////////////////////////// если больше одной буквы или символы /////////////////////////////
				
			} else {
				
				/* ищем введеное в названиях Игр и считаем кол-во найденого */
				
				for (int i = 0; i<arr.size(); i++) {
							
					if (arr.get(i).name.contains(a)) {

						objectList.add(arr.get(i)); // добавляем Объект с совпадением в наш Список
							
						w++;
					}				
				}
					
				if (w>0) {

					return objectList;
					
				};	// если что-то нашлось - возвращаем этот список
		
			}
				
		} // конец if (a.length()>0) - основного
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		return objectList;
				
	} // конец Метода 2.1
		
	/* 2.2 это Метод, который должен проводить сортировку, когда из Издателей,
	 * Мапперов или Годов выбрали один элемент Мышью. Вот по этому в Метод передается еще
	 * и строка - Название Издателя, Маппера или Года
	 * Метод будет собирать совпдения в список и возврашать его */
		
	public static List <TryStuff> sortingListObjedts (List <TryStuff> arr, String s) {
			
		int w = 0; // переменная для отслеживания того, какой список возвращаем
			
		/* создадим Список, который и будет возвращать Метод */
			
		List <TryStuff> objectList = new ArrayList <TryStuff> ();
			
		t++; // вызываем этот метод и t+1 сразу - для отображения этапа Сортировки

		j = s;  // запысываем то, что выбрали Машью в Статич. Переменную
					
		///////////////////// сортировка по полному совпадению по Полям /////////////////////////////
					
		// проходим по списку, и собираем в отдельный список Объекты, которые соответствуют условиям
					
		for (int i = 0; i<arr.size(); i++) {
				
			if ((s.equals(arr.get(i).creator))||(s.equals(arr.get(i).mapper))||(s.equals(arr.get(i).year))) {
					
				objectList.add(arr.get(i)); // добавляем Объект с совпадением в наш Список
					
				w++; 
			}				
		}

		if (w>0) {

			return objectList;
			
		};	// если что-то нашлось - возвращаем этот список
			
	/////////////////////////////////////////////////////////////////////////////////////////////////////

		return objectList;
				
	} // конец Метода 2.2
	
///////////////////////// 2 Метода (3.1 и 3.2) по выводу фоток на экран с Компьютера //////////////////////////////
        
    /* 3.1 Метод по выводу фоток автоматом, когда нашлась 1 Игра на Компьютере,
     * используется в main */
        
    public static void ShowPictures(List <TryStuff> arrg) {
       
    	/* условие, когда фотки показываются только в случае их нахождения на компьютере. Если папка с фотками
    	 * на компе отсутствует, прога будет работать, без их показа */
    	
    	if (MyNewGameFilesFinder.k!=3) {
    		
    		ImageIcon img;
        	
    		String[] pics = TryStuff.gamePics(arrg, TryStuff.m-1);
			
    		/* организуем цикл по выводу фоток на экран с Комрьютера. За один цикл показываем одну фотку.
    		 * Количество циклов зависит от содержания поля amount, для его получения используем метод
    		 * TryStuff.getAmount(arrg, TryStuff.m-1) класса TryStuff. Т.е. сколько фоток столько
    		 * и итераций */
        	
    		for (int i=0; i<TryStuff.getAmount(arrg, TryStuff.m-1); i++) {
        		
    			img = new ImageIcon(pics[i]);
    			JOptionPane.showMessageDialog(null, img, "That's the Game Photo", JOptionPane.PLAIN_MESSAGE);
    		}
        	
    		/* обнуляем переменные */
        	
    		TryStuff.m = 0;
    		TryStuff.t = 0;
    		TryStuff.j3 = "";
    		TryStuff.j2 = "all Games";
        	
    	} else {
    		
    		/* обнуляем переменные */
        	
    		TryStuff.m = 0;
    		TryStuff.t = 0;
    		TryStuff.j3 = "";
    		TryStuff.j2 = "all Games";
    	}
    	
    } // конец метода 3.1
        
    /* 3.2 Метод по выводу фоток выбором путем ввода *_ _ _ c Компьютера,
     * используется в main */
        
    public static void ShowPicturesTough(List <TryStuff> arrg) {
        	
    	/* условие, когда фотки показываются только в случае их нахождения на компьютере. Если папка с фотками
    	 * на компе отсутствует, прога будет работать, без их показа */
    	
    	if (MyNewGameFilesFinder.k!=3) {
    		
    		ImageIcon img;
        	
    		String[] pics = TryStuff.gamePics(arrg, TryStuff.m-1);
        	
    		/* следующее условие служит для проверки адекватности вводимого пользователем номера
    		 * Игры (равному номеру игры в показываемом списке), т.е. число не может быть меньше нуля
    		 * и должно быть в пределах номеров показываемых игр */
        	
    		if (TryStuff.m>0 && TryStuff.m <= arrg.size()) {
        		
    			/* организуем цикл по выводу фоток на экран с Комрьютера. За один цикл показываем одну фотку.
    			 * Количество циклов зависит от содержания поля amount, для его получения используем метод
    			 * TryStuff.getAmount(arrg, TryStuff.m-1) класса TryStuff. Т.е. сколько фоток столько
    			 * и итераций */
        		
    			for (int i=0; i<TryStuff.getAmount(arrg, TryStuff.m-1); i++) {
					
    				img = new ImageIcon(pics[i]);
    				JOptionPane.showMessageDialog(null, img, "That's the Game Photo", JOptionPane.PLAIN_MESSAGE);
    			}
        		
    			/* обнуляем переменные */
        		
    			TryStuff.m = 0;
    			TryStuff.t = 0;
    			TryStuff.j3 = "";
    			TryStuff.j2 = "all Games";
    		}
   	
    		else return;
        
    	} else {
    		
    		/* обнуляем переменные */
    		
			TryStuff.m = 0;
			TryStuff.t = 0;
			TryStuff.j3 = "";
			TryStuff.j2 = "all Games";
    	}
    	
   } // конец Метода № 3.2
   
//////////////////////// 2 Метода (3.3 и 3.4) по выводу фоток на экран с Интернета //////////////////////////////  
    
   /* 3.3 Метод по выводу фоток из Инета, когда нашлась 1 Игра (принимающий Список),
    * используется в main */
        
   public static void WebShowPictures (List <TryStuff> arrg) {
        	
        String[] pics = TryStuff.gamePics(arrg, TryStuff.m-1);
        	
        /* организуем цикл по выводу фоток на экран с Инета. За один цикл показываем одну фотку.
         * Количество циклов зависит от содержания поля amount, для его получения используем метод
         * TryStuff.getAmount(arrg, TryStuff.m-1) класса TryStuff. Т.е. сколько фоток с только
        * и итераций */
        	
        for (int i=0; i<TryStuff.getAmount(arrg, TryStuff.m-1); i++) {
			
			WebFileDownloader.showWebPicture(webAddres + pics[i].replace(" ", "%20").replace(addres, ""));
		}

        /* обнуляем переменные */
        	
        TryStuff.m = 0;
        TryStuff.t = 0;
        TryStuff.j3 = "";
        TryStuff.j2 = "all Games";
        	
   }  // конец метода 3.3
 
   /* 3.4 Метод по выводу фоток выбором путем ввода *_ _ _ только фотки будут браться из Интернета,
    * используется в main */
        
   public static void webShowPicturesTough(List <TryStuff> arrg) {

        String[] pics = TryStuff.gamePics(arrg, TryStuff.m-1);
        	
        /* следующее условие служит для проверки адекватности вводимого пользователем номера
         * Игры (равному номеру игры в показываемом списке), т.е. число не может быть меньше нуля
         * и должно быть в пределах номеров показываемых игр */
        	
        if (TryStuff.m>0 && TryStuff.m <= arrg.size()) {
        		
        	/* организуем цикл по выводу фоток на экран с Инета. За один цикл показываем одну фотку.
	         * Количество циклов зависит от содержания поля amount, для его получения используем метод
	         * TryStuff.getAmount(arrg, TryStuff.m-1) класса TryStuff. Т.е. сколько фоток столько
	         * и итераций */
        		
        	for (int i=0; i<TryStuff.getAmount(arrg, TryStuff.m-1); i++) {
					
				WebFileDownloader.showWebPicture(webAddres + pics[i].replace(" ", "%20").replace(addres, ""));
			}
			
        	/* обнуляем переменные */
        		
			TryStuff.m = 0;
			TryStuff.t = 0;
			TryStuff.j3 = "";
			TryStuff.j2 = "all Games";	
        }
       	
        else return;
        	
   } // конец Метода № 3.4

}  // конец Класса TryStuff
