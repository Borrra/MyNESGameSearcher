import java.awt.Dimension;
import java.awt.Window;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;

//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JList;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.ListModel;
//import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.lang.reflect.Field;

/* в этом классе будут служебные Методы для моего проекта, которые я
 * уберу из Класса TryStuff для его разгрузки + переделаю некоторые 
 * методы под обработку не Массивов а Списков */

public class nesServiceClass {

	/* 9 Методов windowShow/windowShoww по показу Строки, Строки и Строки Заголовка, */
	
	// 1. Метод выводит в Окно передаваюмую ему Строку
	
	public static void windowShow (String arr) { 
    	
    	String text = arr;
		String title = "Let's see what we got here:";			
		
		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 100));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);			
		
	} // конец Метода № 1
	
	/* 2. Метод выводит в Окно передаваюмую ему Строку и вторую передаваемую ему Строку
	      он выводит в заголовок Окна */
	
	public static void windowShow (String arr, String akk) { 
    	
    	String text = arr;
		String title = akk;			

		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 100));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);
		
	} // конец Метода № 2
	
	// 3. Метод выводит в Окно списком Массив Строк
	
	public static void windowShow (String[] arr) { 
    	
    	String text = "";
		String title;			
		
		for (int i=0; i<arr.length; i++) {
			
			String ch = String.format("%03d", i+1);
			text = text + ch + ". "  + arr[i] + "\n";
		}
		
		title = "There're " + arr.length + " elements we have";
		
		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);		
		
	} // конец Метода № 3
	
	// 4. Метод выводит в Окно списком Массив Строк и Заголовок Окна
	
	public static void windowShow (String[] arr, String a) { 
    	
    	String text = "";
		String title;			
		
		for (int i=0; i<arr.length; i++) {
			
			String ch = String.format("%03d", i+1);
			text = text + ch + ". "  + arr[i] + "\n";
		}
		
		title = arr.length + " elements. " + a;
		
		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);		
		
	} // конец Метода № 4
	
	// 5. Метод выводит в Окно списком Список List <String>
	
	public static void windowShoww (List <String> arr) { 
    	
    	String text = "";
		String title;			
		
		for (int i=0; i<arr.size(); i++) {
			
			String ch = String.format("%03d", i+1);
			text = text + ch + ". "  + arr.get(i) + "\n";
		}
		
		title = "There're " + arr.size() + " elements we have";
		
		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);	
		
	} // конец Метода № 5
	
	// 6. Метод выводит в Окно списком Список List <String> и Заголовок Окна
	
	public static void windowShoww (List <String> arr, String a) { 
    	
    	String text = "";
		String title;			
		
		for (int i=0; i<arr.size(); i++) {
			
			String ch = String.format("%03d", i+1);
			text = text + ch + ". "  + arr.get(i) + "\n";
		}
		
		title = arr.size() + " elements. " + a;
		
		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);	
		
	} // конец Метода № 6

    /* 7. Метод по выводу Имен Массива Объектов TryStuff[] в Окно (JOptionPane) со Слайдером
     * в заголовок передаются Статич. переменные чтобы значть что ищем */
	
    public static void windowShow (TryStuff[] arrg) { 
    	
    	String text = "";
		String title;			
		
		for (int i=0; i<arrg.length; i++) {
			
			String ch = String.format("%03d", i+1);
			text = text + ch + ". "  + arrg[i].getName() + "\n";
		}
		
		// формируем надпись в заголовке Окна используя Статич. Переменные j и k
		
		title = "There're " + arrg.length + " elements found. Serching for \"" + TryStuff.j + "\"" + TryStuff.k;
		
		if (arrg.length == 0) {
			
			text = "\n\tNothing is found, dude!";
		}
			
		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);	
		
		if (arrg.length == 0) {

			TryStuff.t = 0;
			return;
		}
		
	} // конец метода № 7
    
    // 8. Метод по выводу Имен Списка Объектов List <TryStuff> в Окно (JOptionPane) со Слайдером
	
    public static void windowShow (List <TryStuff> arrg) { 
    	
    	String text = "";
		String title;			
		
		for (int i=0; i<arrg.size(); i++) {
			
			String ch = String.format("%03d", i+1);
			text = text + ch + ". "  + arrg.get(i).getName() + "\n";
		}
		
		// формируем надпись в заголовке Окна используя Статич. Переменные j и k
		
		title = "There're " + arrg.size() + " elements found. Экспериментальное окно";
		
		if (arrg.size() == 0) {
			
			text = "\n\tNothing is found, dude!";
		}
			
		JTextArea textArea = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(600, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);	
		
		if (arrg.size() == 0) {

			TryStuff.t = 0;
			return;
		}
		
	} // конец метода № 8
 
    // 9. Метод по выводу в Окно (JOptionPane) Одного Объекта TryStuff (информации о нем)
    
    public static void windowShow (TryStuff arrg) {
		
    	String text = "";
		String title;			

			text = "  " + text  +  "\n"
					         + "        Creator: "  + arrg.getCreator() + "\n"
					         + "        Maper:   "  + arrg.getMapper() + "\n"
							 + "        Year:     "  + arrg.getYear() + "\n"
			                 + "        Comment: "  + arrg.getComment() + "\n\n";
		
		// формируем надпись в заголовке Окна используя Статич. Переменные j и k
		
		title = arrg.getName();
		
		JTextArea textArea = new JTextArea(text);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);
		
	} // конец Метода № 9
    
    /* 10. Метод по выводу инфрмации по использованию Приложения */
    
    public static void informWindow () {
    	
    	String text = " Вы ввели ' test ', вот что нужно знать:\n\n"
		    + " creators                  - вывод списка всех Издателей\n"
		    + " издатели\n\n"
		    + " mappers                 - вывод списка всех Мапперов\n"
		    + " мапперы\n\n"
		    + " years                      - вывод списка всех Годов выпуска\n"
		    + " года или годы\n\n"
            + " end или конец       - выход из Программы\n";

    	JTextArea textArea = new JTextArea(text);
    	textArea.setEditable(false);
    	textArea.setLineWrap(true);
    	textArea.setWrapStyleWord(true);
    	JScrollPane scrollPane = new JScrollPane(textArea);
    	scrollPane.setPreferredSize(new Dimension(600, 500));

    	JOptionPane.showMessageDialog(null, scrollPane, "Pay attention! Important Information!", JOptionPane.PLAIN_MESSAGE);

    } // конец Метода № 10
    
    /* 11. Метод по получению Массива Строк Определенного Поля из Массива Объектов TryStuff,
     * название нужного Поля передается вторым Аргументом */
    
    public static List <String> getFieldList (List <TryStuff> objects, String fieldName) {
 
    	if (fieldName.equals("creator"))      {  TryStuff.z1 = 1;  }
    	else if (fieldName.equals("mapper"))  {  TryStuff.z1 = 2;  }
    	else if (fieldName.equals("year"))    {  TryStuff.z1 = 3;  }
    	
    	List <String> list = new ArrayList <String> ();

    	Set <String> set = new TreeSet<>();
    	
    	for (int i = 0; i < objects.size(); i++) {
    		
    		try {
    			
    			Field field = TryStuff.class.getDeclaredField(fieldName);
    			field.setAccessible(true); // Allow access to private fields
    			
    			list.add( (String) field.get(objects.get(i)) );    			
    				
    		} catch (NoSuchFieldException e) {
    			
    			System.out.println("Field not found: " + fieldName);
    			
    			return list; // Return empty array if field is not found
    			
    		} catch (IllegalAccessException e) {
    			
    			e.printStackTrace();
    			return list; // Return empty array if access fails
    		}
    	}
    	
    	//windowShoww (list);
	
    	set.addAll(list);
    	
    	list.clear();
    	
    	list.addAll(set);
    	
    	set.clear();
    	
    	//windowShoww (list);
    	
    	/* если есть факт того, что был просмотрен список Изд, Мап или Годов (t1=1), и факт выбора чего-лио
    	 * из этого списка (z1 == 1, 2 или 3), то все, можно обнулять z1 и t1 */
    	
    	if ( (TryStuff.z1 == 1 && TryStuff.t1 == 1) || 
    		 (TryStuff.z1 == 2 && TryStuff.t1 == 1) ||
    		 (TryStuff.z1 == 3 && TryStuff.t1 == 1) )  {
    		
    		TryStuff.z1 = 0;
    		TryStuff.t1 = 0;   		
    	}
    	
    	return list;
    	
    } // конец Метода № 11
    
    /* 12. Метод по обработке Кода "*_ _ _" по которому в Проекте происходит выбор чего-либо из списка.
     * Принимает Строку и если она начинается с "*" и возвращает true (если это число) если оно
     * соответствующей длины и не 0 */
    
    public static boolean isStarCodeOk (String k) {
 	
    	boolean w = false; // то, что возвращаем
    	int e = 4; // максимальный разряд проверяемого числа включая '*' ( *999 )
    	
    	if (k.equals("")) { // вот без этог условия у меня была ошибка!
    		
    		//System.out.println("Here this shit!");
    		
    		return w;   		
    	}
    	
    	char[] chr = k.toCharArray();
    	
    	if ( (k.charAt(0)=='*') && (k.length()>1 && (k.length() <= e)) && chr[1] != '0' ) {

    		int r = 0;
    		
    		for (int i=1; i<k.length(); i++) {
    			
    			if ( Character.isDigit(chr [i])) {
 
    				r++;
    			}
    		}
    		
    		if (r == k.length()-1) {
    			
    			w = true;
    		}
    	}
    	
    	return w;
    	
    } // конец Метода № 12
  
 ////////////////////////// 3 Метода по выбору Игр мышью из списка /////////////////////////////////
    
    /* 13. Метод по выводу Массива Строк в Окно (JOptionPane) со Слайдером и
    возможностью выбора элементов из этого списка Мышью! - это для Издателей, Мапперов и Годов */
	
    public static void choosingWithMouseWindow(List <String> arr) { 
    
    	TryStuff.t1 = 1; // если смотрим список Издателей, Мапперов или Годов, то t1 +1 (для отслеживания в main) 
    
    	if (arr.size() == 0) {
    		nesServiceClass.windowShow ("There's no elements in this array.");
			return;
    	}
 	
		String title;	  // то, что будет написано в заголовке нашего Окна

		 DefaultListModel <String> listModel = new DefaultListModel<>();
		 
		// добавляем наш Массив Строк в наш JList
		
		 for (String item : arr) {
			 
			 listModel.addElement(item);
		 }
		 
		// создаем JList - myList
			
		JList <String> myList = new JList<> (listModel);

		// создаем типо "Счетчик" к нашму JList
		
		myList.setCellRenderer (new NumberedListCellRenderer());
		
		// создаем Панель Прокрутки и добавляем туда наш Список myList
		
		JScrollPane scrollPane = new JScrollPane(myList);
		
		myList.addListSelectionListener(new ListSelectionListener() { // начало Метода регистрации Приемника
			
			public void valueChanged(ListSelectionEvent e) { // начало Метода регистрации События
				
				if (!e.getValueIsAdjusting()) {
					
					// получаем один выбранный Мышкой Элемент (Строку)
					
					String selectedElement = myList.getSelectedValue();
					
					// получаем Индекс (номер выбранной Строки)
					
					int index = -1;
					
					ListModel <String> model = myList.getModel();
					
					for (int i = 0; i <model.getSize(); i++) {
						if (model.getElementAt(i).equals(selectedElement)) {
							index = i;
							break;
						}
					}
					
					// по Индексу выбранной Строки, выбираем эту Строку из нашего Массива Строк
					
					String shit = arr.get(index);
					
///////////////// вот здесь и прописываем основной Экшен для выбранного элемента Массива Строк ////////////////////
										
					TryStuff.k1 = shit;	// присваивем выбранную Строку переменной k1				

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				/* Эту часть подсказал GPT, это мгновенно закрывает Окно при нажатии на один из элементов Списка */
					
					Window window = SwingUtilities.getWindowAncestor (myList);
					
					if (window instanceof JDialog) {
						JDialog dialog = (JDialog) window;
						dialog.dispose();
					}
					
					else if (window instanceof JFrame) {
						JFrame frame = (JFrame) window;
						frame.dispose();
					}
					
				} // конец if цикла
			}  // конец метода Внутреннего Абстрактного Класса, т.е. конец Метода регистрации События
		});  // конец нашего Внутреннего Абстрактного Класса, т.е. Метода регистрации Приемника			
			
		// формируем надпись в заголовке Окна используя Статич. Переменные j2, j3 и m1

		title = "     You just chose the '" +TryStuff.j+ "' category. Let's choose one of them!";
		
		scrollPane.setPreferredSize(new Dimension(500, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);	

	} // конец Метода № 13
    
    /* 14. Метод по выводу Имен Массива Объектов TryStuff[] в Окно (JOptionPane) со Слайдером и
    возможностью выбора элементов из этого списка Мышью! Вот над созданием этого метода,
  * этого Окна я бился долгие месяцы! */

    public static void choosingWithMouseWindoww (List <TryStuff> arr) { 

    	String title;	  // то, что будет написано в заголовке нашего Окна

    	// создаем Массив Строк "y" из нашего Массива Объектов 
		
    	List <String> y = new ArrayList <String> ();
		
    	for (int i = 0; i<arr.size(); i++) {
		
    		y.add (arr.get(i).getName());
    	}
	
    	// присваиваем m1 значение равное длине нашего Массива
	
    	TryStuff.m1 = y.size();
	
    	// добавляем наш Массив Строк 'y' в наш JList
	
    	DefaultListModel <String> listModel = new DefaultListModel<>();
	 
    	// добавляем наш Массив Строк в наш JList
		
    	for (String item : y) {
			 
    		listModel.addElement(item);
    	}
		 
    	// создаем JList - myList
			
    	JList <String> myList = new JList<> (listModel);
	
    	// создаем типо "Счетчик" к нашму JList
	
    	myList.setCellRenderer (new NumberedListCellRenderer());
	
    	// создаем Панель Прокрутки и добавляем туда наш Список myList
	
    	JScrollPane scrollPane = new JScrollPane(myList);
	
    	/////////////// Регистрация Приемника ///////////////////////////////
	
    	myList.addListSelectionListener(new ListSelectionListener() { // начало Метода регистрации Приемника
		
    		public void valueChanged(ListSelectionEvent e) { // начало Метода регистрации События
			if (!e.getValueIsAdjusting()) {
				
				TryStuff.z4 = 1; // если есть факт выбора Игры из списка Мышью, z4 = 1
				
				// получаем один выбранный Мышкой Элемент (Строку)
				
				String selectedElement = myList.getSelectedValue();
				
				// получаем Индекс (номер выбранной Строки)
				
				int index = -1;
				
				ListModel <String> model = myList.getModel();
				
				for (int i = 0; i <model.getSize(); i++) {
					if (model.getElementAt(i).equals(selectedElement)) {
						index = i;
						break;
					}
				}
				
				// по Индексу выбранной Строки, выбираем Объект из нашего Массива Объектов
				
				TryStuff shit = arr.get(index);
				
				// Оъект в окно (полная инфа об Игре) если элементов более 1
				
				if (TryStuff.m1 > 0) {
					
					nesServiceClass.windowShow(shit);
				}
				// показываем Фотографии выбранного Объекта (Игры)
				
				ImageIcon img;	
				
				String[] pics = shit.gamePics(arr, index);
		
	    /* блок действий с выбранным мышью элементом (показ фоток) */
				
				/* организуем цикл по выводу фоток на экран с Комрьютера. За один цикл показываем одну фотку.
	        	 * Количество циклов зависит от содержания поля amount, для его получения используем метод
	        	 * TryStuff.getAmount(arrg, TryStuff.m-1) класса TryStuff. Т.е. сколько фоток столько
	        	 * и итераций */
				
				for (int i =0; i<TryStuff.getAmount(arr, index); i++) {
					
					img = new ImageIcon(pics[i]);
					JOptionPane.showMessageDialog(null, img, "That's the Photo", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}  // конец метода Внутреннего Абстрактного Класса, т.е. конец Метода регистрации События
	});  // конец нашего Внутреннего Абстрактного Класса, т.е. Метода регистрации Приемника			

/////////////////////// Конец Регистрации Приемника //////////////////////////////
	
    	// формируем надпись в заголовке Окна используя Статич. Переменные j2, j3 и m1

    	title = "            Sorted by: " + TryStuff.j2 + TryStuff.j3  + " (" + TryStuff.m1 + " elements)";
	
    	scrollPane.setPreferredSize(new Dimension(500, 500));

    	JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);	
	
    	if (arr.size() == 0) {

    		TryStuff.t = 0;
    		return;
    	}
	
    } // конец Метода № 14
    
    /* 15. Метод по выбору игры мышью, при загрузке фоток из Интернета */
    
    public static void webChoosingWithMouseWindow(List <TryStuff> arrg) { 
     	
		String title;	  // то, что будет написано в заголовке нашего Окна
		
		 /* создаем Список Имен игр из нашего Списка Оъбектов */
		
		 List <String> stringList = new ArrayList <String> ();
		 
		 for (int i=0; i<arrg.size(); i++) {
			 
			 stringList.add(arrg.get(i).getName());
		 }
		
		// присваиваем m1 значение равное длине нашего Массива
		
		TryStuff.m1 = stringList.size();
		
		// добавляем наш Список Строк 'stringList' в наш JList
		
    	DefaultListModel <String> listModel = new DefaultListModel<>();
	 
    	// добавляем наш Массив Строк в наш JList
		
    	for (String item : stringList) {
			 
    		listModel.addElement(item);
    	}
		 
    	// создаем JList - myList
			
    	JList <String> myList = new JList<> (listModel);

		// создаем типо "Счетчик" к нашму JList
		
		myList.setCellRenderer (new NumberedListCellRenderer());
		
		// создаем Панель Прокрутки и добавляем туда наш Список myList
		
		JScrollPane scrollPane = new JScrollPane(myList);
		
		myList.addListSelectionListener(new ListSelectionListener() { // начало Метода регистрации Приемника
			
			public void valueChanged(ListSelectionEvent e) { // начало Метода регистрации События
				if (!e.getValueIsAdjusting()) {
					
					TryStuff.z4 = 1; // если есть факт выбора Игры из списка Мышью, z4 = 1
					
					// получаем один выбранный Мышкой Элемент (Строку)
					
					String selectedElement = myList.getSelectedValue();
					
					// получаем Индекс (номер выбранной Строки)
					
					int index = -1;
					
					ListModel <String> model = myList.getModel();
					
					for (int i = 0; i <model.getSize(); i++) {
						if (model.getElementAt(i).equals(selectedElement)) {
							index = i;
							break;
						}
					}
					
					// по Индексу выбранной Строки, выбираем Объект из нашего Массива Объектов
					
					TryStuff shit = arrg.get(index);
					
					// Оъект в окно (полная инфа об Игре) если элементов более 1
					
					if (TryStuff.m1 > 0) {
						
						nesServiceClass.windowShow(shit);
					}
					
					// показываем Фотографии выбранного Объекта (Игры)
					
					String[] pics = shit.gamePics(arrg, index);
					
					/* организуем цикл по выводу фоток на экран с Инета. За один цикл показываем одну фотку.
		        	 * Количество циклов зависит от содержания поля amount, для его получения используем метод
		        	 * TryStuff.getAmount(arrg, TryStuff.m-1) класса TryStuff. Т.е. сколько фоток столько
		        	 * и итераций */
					
					for (int i=0; i<TryStuff.getAmount(arrg, index); i++) {
						
						WebFileDownloader.showWebPicture(TryStuff.webAddres + pics[i].replace(" ", "%20").replace(TryStuff.addres, ""));
					}
					
				}
				
			}  // конец метода Внутреннего Абстрактного Класса, т.е. конец Метода регистрации События
		});  // конец нашего Внутреннего Абстрактного Класса, т.е. Метода регистрации Приемника			
			
		// формируем надпись в заголовке Окна используя Статич. Переменные j2, j3 и m1

		title = "            Sorted by: " + TryStuff.j2 + TryStuff.j3  + " (" + TryStuff.m1 + " elements)";
		
		scrollPane.setPreferredSize(new Dimension(500, 500));

		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.PLAIN_MESSAGE);	
		
		if (arrg.size() == 0) {

			TryStuff.t = 0;
			return;
		}
		
	}  // конец метода 15
    
    /* 16. Простой Метод Да/Нет, возвращает 0 - если Да, 1 - если Нет, и -1 - если Крестик */
	
	public static int yesNoWindow () {
		
		// Show a dialog with Yes and No options
		
        int response = JOptionPane.showConfirmDialog(null, 
                "Скачиваем папку с Фотками с Инета?", 
                "Выберите вариант", 
                JOptionPane.YES_NO_OPTION);
        
        return response;
	}
    
} // конец Класса
