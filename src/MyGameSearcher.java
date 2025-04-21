
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

/* Главный Класс Проекта с главным Методом main Это экспериментальный Прокет
 * Перевожу все на Списки, избавляюсь от Массивов.
 * Сейчас (26.12.24) заработало, только без Инета. Т.е. запускаем, вводим:
 * "Inet Off" и работаем */

public class MyGameSearcher {
	
	public static int choice = 0;
	public static int webOrNot = 0;
	
	public static void main(String[] args) {
		
		/* чекаем есть ли вообще Интернет или нет, если есть, то считываем текстовый файл из Инета и пытаемся
		 * создать его на рабочем, если его еще не было, если был, используем тот что был */
		
		if ( WebFileDownloader.isInternetAvailable() ) { // при запуске Инет есть
			
			/* Применение этого метода Обновляет Текстовый файл проекта, т.е. считывает инфу с GitHub и формирует
			 * текстовый файл с инфой для моей проги и перезаписывает (или создает, если его не было) его в моей
			 * папке на рабочем столе */
			
			/* потом нужно будет сделать так, чтобы этот метод активировался по введению в строку какой-либо
			 * команды и происходило обновление текстового файла */
			
			//WebFileDownloader.fileFromWebCreator();
			
			webOrNot = 1; // 1 - если Интернет есть
	
			/* потом нужно будет сделать так, чтобы этот метод активировался по введению в строку какой-либо
			 * команды и происходило обновление Папки с Фотографиями Проекта */

	
		} else { // если при запуске проги Инет отсутствует
			
			/* это else срабатывает только один раз при запуске прогрммы, если в этот момент отсутствует
			 * подключение к Интернету */
			
			/* если интернета нет, то об этом напишется и метод MyGameFileAddress - будет проверять есть ли
			 * файлы на компе и осуществлять поиск и загрузку.
			 * если и на компе ничего не найдется - конец программы. */
			
			nesServiceClass.windowShow("Интернет соединение отсутствует. Ищем на компе.");
			
			//MyGameFilesFinder.MyGameFilesAddress ();
			//MyNewGameFilesFinder.MyNewGameFilesAddress ();
			MyNewGameFilesFinder.MySuperGameFilesAddress ();
			
			/* по окончании метода MySuperGameFilesAddress, если Текст файл не найден (вне зависимости от того,
			 * найдена Папка с фотками или нет) choice = 1, т.е. программа завершается */
			
		}
			
	/* мой новый Класс по проверке нахождения исходной папки проекта. Передаем туда название исходной папки,
	 * которая сейчас лежит на рабочем столе, и  если она существует, пишем об этом и запускается прога, если на
	 * рабочем столе этот метод папку не найдет, то он будет искать ее на всем компьютере и если найдет,
	 * начнет программу, если же вообще нигде не найдет,
	 * то напишет об этом и прога закончится, так как choice будет равент 1 */

		/* если файлы на рабочем столе, то
		 * запуск идет оттуда, если нет, то идет проверка скрытого текстового файла на рабочем столе на наличие
		 * двух строк нужных нам адресов, если они есть, запуск проги идет с этих адресов, если и там нет (в текстовом
		 * вайле их нет, или нет самого текстового файла) идет поиск по всему компу и если файлы находятся, то эти
		 * адреса прописываются в текстовый файл и происходит запуск.
		 * Соответственно при послудеющих запусках, адреса файлов будут считываться
		 * из текстового файла (в обход долгого поиска).
		 * При наличии файлов на рабочем столе - просто идет запуск */
		
		//MyGameFilesFinder.MyGameFilesAddress ();
		
		List <TryStuff> games; // создаем список под наш Массив объектов TryStuff
		List <TryStuff> first;
		List <TryStuff> second;
		List <TryStuff> third;
			
		while (choice == 0) { // начало нашего основного цикла 
			
			if (webOrNot == 0) { // ищем на Компе
				
				/* формирование Списка games посредством поиска на компьютере */
				
				//MyGameFilesFinder.MyGameFilesAddress ();
				//MyNewGameFilesFinder.MyNewGameFilesAddress ();

				/* если это запуск программы к==0, ищем файлы на компе. Если ничего не нашлось то в следующий раз
				 * уже не будем искать */
				
				if (MyNewGameFilesFinder.k==0) {
					
					MyNewGameFilesFinder.MySuperGameFilesAddress ();
				}
				
				/* по окончании метода MySuperGameFilesAddress, если Текст файл не найден (вне зависимости от того,
				 * найдена Папка с фотками или нет) choice = 1, т.е. программа завершается */
				
				/* условие, если перешли на работу с компа, а текст файл не найден -
				 * переходим на поиск с Инета */
				
				if (choice==1 && WebFileDownloader.isInternetAvailable()) {
					
					/* вот здесь то и надо размещать блок по скачиванию папки проекта
					 * с играми, т.е. когда инет есть, мы перешли на поиск файлов на
					 * компе, а оказалось, что папка отсутствует.
					 * Надо предложить скачать все файлы с Инета (Git Hub) */
					
					// скачаем Текстовый файл
					
					TryStuff.fileAddres = TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.textFileName;
					
					WebFileDownloader.fileFromWebCreator();
					
					if (nesServiceClass.yesNoWindow() == 0) {
						
						////////////////// блок скачаваиня всей папки с фотографиями /////////////
						
						/* тут нужно окно с выбором: либо скачиваем все фотографии, либо
						 * запускаемся с Инета 
						 * если качаем, то WebPhotoFolderDownload.downloadFullFolder(); 
						 * если смотрим с Инета, то просто далее */
						
						TryStuff.addres = TryStuff.desktopPath + File.separator + TryStuff.projectFolderName + File.separator + TryStuff.photoFolderName;
						
						WebPhotoFolderDownload.downloadFullFolder();
						
						continue;
						
					} else {
						
						/* в этот блок мы перешли, если мы перешли вручную на работу с компа, на компе ничего нет,
						 * текст файл скачался, затем мы отказались скачивать фотки. 
						 * Может тут не надо запускатья с инета, а надо продолжать работу без фоток на компе?..
						 * может все-таки webOrNot = 0 ?.. */
						
						choice = 0;   // запрещаем заканчивать программу
						webOrNot = 1; // поиск в Инете
						
						nesServiceClass.windowShow("Запускаемся с Инета");
						continue;
					}
		
				} // конец if (choice==1 && WebFileDownloader.isInternetAvailable())
				
				/* здесь формируется Список моих объектов, при чтении инфы С КОМПЬЮТЕРА */
				
				games = GamesReader.readGamesFromFile(TryStuff.fileAddres); 

			} else { // поиск в Интернете, если он есть (т.е. если webOrNot == 1 )
				
				if ( WebFileDownloader.isInternetAvailable() ) {
					
					/* формирование Списка games путем скачивания инфы из моего файла на GitHub и формирования объектов
					 * непосредственно из него, не создавая никакого текстового файла. Т.е. каждый цикл инфа обновляется
					 * ИЗ ИНТЕРНЕТА */
					
					games = WebGamesReader.readGamesFromWeb();
					
				} else { // если установили вручную webOrNot = 1, а Инета нет
					
					/* если интернета нет, то об этом напишется и метод MyGameFileAddress - будет проверять есть ли
					 * файлы на компе и осуществлять поиск и загрузку */
					
					nesServiceClass.windowShow ("Интернет соединение отсутствует. Поищем на компе.");
		
					//MyGameFilesFinder.MyGameFilesAddress (); // мой "старый" метод поиска файлов (в папке GameSearch)
					//MyNewGameFilesFinder.MyNewGameFilesAddress (); // мой новый метод поиска файлов независимо
					MyNewGameFilesFinder.MySuperGameFilesAddress ();
					
					if (choice==1) { // это если инет пропал и на компе ничего не нашлось - конец проги
						
						nesServiceClass.windowShow ("Инет пропал и на компе ничего - это эпик фэйл бро!");
						continue;
					}
					
					/* если на компе что-то найдется - будет формироваться массив games (чтение С КОМПЬЮТЕРА */
					
					games = GamesReader.readGamesFromFile(TryStuff.fileAddres);
					
					webOrNot = 0; // если вручную поставили 1, а Инета нет, меняем опять на 0
				}
			}
		
			///////////////// до этого момента все как и в основном проекте ////////////////////
			
			////////////////////////////// Эксперимент //////////////////////////////////
					
			//TryStuff.sortingListObjedts (games); // экспериментирую
			
			//nesServiceClass.getFieldArray(games, "creator");

			/////////////////////////////////////////////////////////////////////////////

			/* если k1="", значит из Издателей, Мапперов и др. ничего не выбрали ни Мышью, ни Стар-Кодом,
			 * поэтому происходит "обчный" поиск из полноно Массива (либо Списка) */

			/* первое применение (если ничего не смотрели и не выбирали) */
			
			if ( TryStuff.k1.equals("") ) {

				/* формируем первый список, отсортированных по чему-либо объектов в случае
				 * первого запуска проги (k1="") */
				
				first = TryStuff.sortingListObjedts (games);

				/* Вот здесь будем формироват новый отсортированный Список */

				TryStuff.j2 = "all Games";
				
				/* если посмотрели список Издателей и др. ничего не выбрали и в открывшемся начальном окне
				 * ничего не ввели была ошибка, пока не обнулил t1 и z1 */	
			}

			/* если k1= чему-либо, значит из Издателей, Мапперов или Годов выбрали что-то Мышью (a t1 и z1 = 1) либо
			 * Стар-Кодом, поэтому массив firstNumberArray формируем Методом letsSortObjects1, по вводу нужного нам
			 * указателем Мыши, при этом
			  статические переменные (t1, z1) которые +1 при запуске методов TryStuff.creators/mappers/years и
			  TryStuff.choosingWithMouseWindow(String[]) нужно обнулить! */
			
			/* поэтому этот как-бы "второе" применение метода TryStuff.letsSortObjects, только с 2 аргументами */
			
			else {

				/* формируем первый список, отсортированных по чему-либо объектов в случае
				 * запуска проги после просмотра списков Издателей и др. ( например k1 = "MMC-1" ) */
				
				first = TryStuff.sortingListObjedts(games, TryStuff.k1);
				
				TryStuff.k1 = "";
				
			// обнуляем статические переменные 
				
				TryStuff.t1=0;
				TryStuff.z1=0;		
			}
					
	    /* если сначала посмотрели список Издателей (z1=1), Мапперов (z1=2) или Годов (z1=3) (то ест t1+1 в TryStuff.choosingWithMouseWindow)
	     * и потом в Окне поиска ввели код *_ _
	     * (т.е. номер элемента из списка), то включается поиск по номеру выбранного Издателя, Маппера или Года */

		/* здесь происходит как-бы второе применение методов nesServiceClass.getFieldArray 
		 * первое происходит ниже, при просмотре списков Издателей, Мапперов, Готов и вот там то и происхоит изменение переменной z1. */
			
	///////////// По Издателям: если вводим Код *_ _ , то это число пишется в переменную m, а потом по этому номеру выбираем Элемент из Массива Creators
				
			if ( (TryStuff.z1 == 1)&&(TryStuff.t1 == 1) ) {
				
				if ( nesServiceClass.isStarCodeOk(TryStuff.j) ) {
					
					TryStuff.m = Integer.parseInt(TryStuff.j.substring(1));
					
					List <String> li = nesServiceClass.getFieldList (games, "creator");
						
					if (TryStuff.m-1 < li.size()) {
							
						TryStuff.k1 = li.get(TryStuff.m-1);
							
						TryStuff.t=0;
						TryStuff.m=0;				
						continue;
					}
				}
				
				TryStuff.z1 = 0;
				TryStuff.t1 = 0;					
			} 	

	///////////// По Мапперам: если вводим Код *_ _ , то это число пишется в переменную m, а потом по этому номеру выбираем Элемент из Массива Creators
				
				if ( (TryStuff.z1 == 2)&&(TryStuff.t1 == 1) ) {
					
					if ( nesServiceClass.isStarCodeOk(TryStuff.j) ) {
						
						TryStuff.m = Integer.parseInt(TryStuff.j.substring(1));
						
						List <String> li = nesServiceClass.getFieldList (games, "mapper");
							
						if (TryStuff.m-1 < li.size()) {
								
							TryStuff.k1 = li.get(TryStuff.m-1);
								
							TryStuff.t=0;
							TryStuff.m=0;				
							continue;
						}
					}
					
					TryStuff.z1 = 0;
					TryStuff.t1 = 0;					
				} 			

		///////////// По Годам: если вводим Код *_ _ , то это число пишется в переменную m, а потом по этому номеру выбираем Элемент из Массива Creators
				
				if ( (TryStuff.z1 == 3)&&(TryStuff.t1 == 1) ) {
					
					if ( nesServiceClass.isStarCodeOk(TryStuff.j) ) {
						
						TryStuff.m = Integer.parseInt(TryStuff.j.substring(1));
						
						List <String> li = nesServiceClass.getFieldList (games, "year");
							
						if (TryStuff.m-1 < li.size()) {
								
							TryStuff.k1 = li.get(TryStuff.m-1);
								
							TryStuff.t=0;
							TryStuff.m=0;				
							continue;
						}
					}
					
					TryStuff.z1 = 0;
					TryStuff.t1 = 0;					
				} 	
				
		///////////////////////////////// блок ввода спец. команд в начальное окно ввода /////////////////////////////
				
				// 1. если вводим mappers выводится список Мапперов, из которого можно выбирать нужный Маппер Мышью
				
				if (TryStuff.j.equals("creators")||(TryStuff.j.equals("издатели"))) {
					TryStuff.k =".";

					List <String> Creators = nesServiceClass.getFieldList (games, "creator"); // TryStuff.z1 = 1
					
					nesServiceClass.choosingWithMouseWindow(Creators); // здесь формируется переменная TryStuff.k1 и TryStuff.t1=1 

					TryStuff.t = 0;

					continue;
				}
				
				// 2. если вводим creators выводится список Издателей, из которого можно выбирать нужный Маппер Мышью
				
				if (TryStuff.j.equals("mappers")||(TryStuff.j.equals("мапперы"))) {
					TryStuff.k =".";

					List <String> Mappers = nesServiceClass.getFieldList (games, "mapper"); // TryStuff.z1 = 2
					
					nesServiceClass.choosingWithMouseWindow(Mappers); // здесь формируется переменная TryStuff.k1 и TryStuff.t1=1

					TryStuff.t  = 0;
					
					continue;
				}
						
				// 3. если вводим years выводится список Годов выпуска игр, из которого можно выбирать нужный Маппер Мышью
				
				if (TryStuff.j.equals("years")||(TryStuff.j.equals("года"))||(TryStuff.j.equals("годы"))) {
					TryStuff.k =".";

					List <String> Years = nesServiceClass.getFieldList (games, "year"); // TryStuff.z1 = 3
					
					nesServiceClass.choosingWithMouseWindow(Years); // здесь формируется переменная TryStuff.k1 и TryStuff.t1=1
					
					TryStuff.t = 0;

					continue;
				}
				
				// 5. если вводим test или тест выводится список опций ввода
				
				if (TryStuff.j.equals("test")||(TryStuff.j.equals("тест"))) {
					
					nesServiceClass.informWindow();
					
					TryStuff.t = 0;
					continue;
				}
				
				/* 6. если вводим "InetOff" переменная webOrNot = 1 и как-бы отключаем закачку даных из Инета */
				
				if (TryStuff.j.equals("InetOff")) {
					
					webOrNot = 0;
					
					TryStuff.t = 0;
					continue;
				}
				
				/* 7. если вводим "InetOn" переменная webOrNot = 0 и как-бы включаем закачку даных из Инета */
				
				if (TryStuff.j.equals("InetOn")) {
					
					webOrNot = 1;
					
					TryStuff.t = 0;
					continue;
				}
				
				/* 8. если вводим "refreshFile" запускается метод по обновлению Текстово Файла проекта
				 * с Git Hub, потом программа завершаетя и требуетя запустить ее снова */
				
				if ( TryStuff.j.equals("refreshFile") ) {
					
					WebFileDownloader.fileFromWebCreator();
					
					nesServiceClass.windowShow ("Требуется перезапуск приложения.");
					
					break;
				}
				
				/* 9. если вводим "refreshPhoto" запускается метод по обновлению Папки с фотографиями на Компе
				 * здесь еще надо обработать вариант, когда папка на рабочем отсутствует, иначе выпадает ощибка */
				
				if ( TryStuff.j.equals("refreshPhoto") && WebFileDownloader.isInternetAvailable()) {
					
					WebPhotoFolderDownload.differListPhotoToDownload();
					
					TryStuff.t = 0;
					continue;
				}
				
				/* 10. при вводе "FilesAddress" на экран выводится адреса файлов проекта - для понимания, откуда
				 * берется информация на Компе */
				
				if ( TryStuff.j.equals("FilesAddress")||(TryStuff.j.equals("АдресФайлов")) ) {
					
					nesServiceClass.windowShow (TryStuff.fileAddres + "\n" + TryStuff.addres, "Адреса наших файлов:");
									
					TryStuff.t = 0;
					continue;
				}
				
				// 11. если вводим 'end' - выход из Проги
				
				if (TryStuff.j.equals("end")||(TryStuff.j.equals("конец"))) {

					break;
				}
				
		//////////////////////// 12. вводя "exper" можно проверить какую-то часть кода /////////////////////////////////
				
				if ( TryStuff.j.equals("exper") ) {

					//WebPhotoFolderDownload.checkLocalFolder();
					
//					String ka = WebPhotoFolderDownload.minusLastPart(TryStuff.addres, "\\\\");
//					
//					File shit = new File (ka);
//					
//					if (shit.exists()) {
//						nesServiceClass.windowShow(ka, "существует");
//						
//					}
					
//					try {
//						
//						WebPhotoFolderDownload.downloadOneFile("https://raw.githubusercontent.com/Borrra/GamesPhoto/main/Dizzy/3.jpg", TryStuff.addres + "Addruga/3.jpg");
//					
//					} catch (IOException e) {
//					
//						e.printStackTrace();
//					}
					
					//WebPhotoFolderDownload.downloadFullFolder();
					
					//WebPhotoFolderDownload.differListPhotoToDownload();
									
					break;
				}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				/* вот это первое применение Метода TryStuff.sortedObjectArray, т.е. это первый список формирующийся здесь
				 * (не выводящийся на экран, а только формирующийися). Мой метод по поиску и формировнию Списка, и есть
				 * как-бы аналог этого метода, только у меня формируется Список. И вот его, я уже смогу передвать в 
				 * Методы choosingWithMouseWindow и webChoosingWithMouseWindow, только нужн их переделать под Списки */
				
				/* по номерам элементов выбираем их из полного Массива Имен, т.е. firstSortedObjectArray - это Первый список
				   который выводится на экран, тоесть там может быть сколко угодно элементов */ 

				/* если на начальном запросе что-либо ввели, то это что-то записываем в переменную j2
				 * для отображения в заголовке окна того по чему отсортированно */
				
				if (!TryStuff.j.equals("")) {
					
					TryStuff.j2 = TryStuff.j;
					
				} else {

					/* если на начальном запросе вводим "ничего" получаем список всех игр */
					
					first = games;
				}
				
				/* вот тут происходит первый вывод на экран списка найденных элементов */

				if (webOrNot == 0) { // инета нет

					nesServiceClass.choosingWithMouseWindoww(first);
					
				} else {
					
					nesServiceClass.webChoosingWithMouseWindow (first); // вывод фоток с Инета
				}
				
			/* Если из показанного списка Игр выбирали хотябы Одну игру Мышью, т.е. если z4 = 1, то после этого
			 * (после просмотра нескольких игр мышью и нажатия ОК) нас перебрасывает на начальный экран */	
				
				if (TryStuff.z4 == 1) {
					
					TryStuff.z4=0;
					TryStuff.t1=0;
					TryStuff.t=0;
					TryStuff.m=0;
					continue;
				}
				
				// если на Первом Уровне отсортировалась Одна Игра, то при нажатии Enter -> пошли фотки
				
				if (first.size()==1) {

					TryStuff.m = 1;

					nesServiceClass.windowShow (first.get(0));
					
					if (webOrNot == 0) {
						
						TryStuff.ShowPictures(first);
					
						continue;
						
					} else {
			
						TryStuff.WebShowPictures(first); // вывод фоток с Инета
						
						continue;
					}
				}
			
				TryStuff.k = " out of " + TryStuff.j + ".";
				
				// 2 уровень сортировки: если нашлось больше .. елементов в Первом списке, то Второе окно будет
				
				if (first.size()>1) {  // основное условие для 2 уровня сортровки
					
					second = TryStuff.sortingListObjedts (first);
				
					// если ничего не вводим во второй раз, то переходим на начальное Окно запроса
					
					if (TryStuff.j.equals("")) {

						TryStuff.z4=0;
						TryStuff.t1=0;
						TryStuff.t=0;
						TryStuff.m=0;
						continue;
					}
					
					/* в j3 добавляем доп. строчку, чтобы было видно, по чему мы сортируем второй раз.
					 * В строке 470 (примерно), обнулим её  (TryStuff.j3 = ""; ) */
					
					TryStuff.j3 = " and then by: " + TryStuff.j;
					
					if ( TryStuff.j==null || TryStuff.j.trim().isEmpty() ) {
							
						TryStuff.m = 0;   // поиск будет продолжаться дальше
					}
			
					else {
						
						// если при подсортировки вводим Код *_ _ _, то это число пишется в переменную m для вывода Фото игры на экран

						if ( nesServiceClass.isStarCodeOk(TryStuff.j) ) {
							
							TryStuff.m = Integer.parseInt(TryStuff.j.substring(1));
							TryStuff.j3 = "";
							TryStuff.j2 = "all Games";

						}
					}
					
					if ((TryStuff.m > 0) && (TryStuff.m <= first.size())) {
		
						// если выше ввели код *_ _ , то выводится фотки этой Игры (номер которой ввели)
						
						nesServiceClass.windowShow ( first.get(TryStuff.m-1) );
					
					
						if (webOrNot == 0) { // инета нет
						
						/* здесь я делал попытку сделать так, что если папку с фотками не нашли, то и попытки
						 * показывать фотки не нужны */
							
//						if ( (webOrNot ==0) && (!TryStuff.addres.equals("")) ) {
							
							//nesServiceClass.windowShow ( "Это типо нормальный режим" );
							
							TryStuff.ShowPicturesTough(first);
					
							continue;
//						
//						} 
//						
//						else if ( (webOrNot ==0) && (TryStuff.addres.equals("")) ) {
//							
//							nesServiceClass.windowShow ( "Фотачки не показываем" );
//							
//							TryStuff.t = 0;
//							
//							//continue;
//						}					
						
						} else  {
			
							TryStuff.webShowPicturesTough(first); // вывод фоток с Инета
						
							continue;
						}				
					} 
					
					 else if (TryStuff.m > first.size()) {
						TryStuff.t = 0;
						TryStuff.j3 = "";
						TryStuff.j2 = "all Games";
						continue;
					 }
					
					// выводим на экран Вторично отсортированный Массив Объектов (только Названия)

					if (webOrNot == 0) {
						
						nesServiceClass.choosingWithMouseWindoww (second);
						
					} else {
						
						nesServiceClass.webChoosingWithMouseWindow (second);  // вывод фоток с Инета
					}
					
					TryStuff.j3 = ""; // j3 нужно опять "обнулить", чтобы при продолжении поиска (на первой стадии) ее не было видно.
							
				/* Если из показанного списка Игр выбирали хотябы Одну игру Мышью, т.е. если z4 = 1, то после этого Окна
				     нас перебрасывает на начальный экран */
					
					if (TryStuff.z4 == 1) {
						
						TryStuff.z4=0;
						TryStuff.t1=0;
						TryStuff.t=0;
						TryStuff.m=0;
						continue;
					}
					
					// если на Втором Уровне отсортировалась Одна Игра, то при нажатии Enter -> пошли фотки
					
					if (second.size()==1) {

						TryStuff.m = 1;
						
						nesServiceClass.windowShow (second.get(0));
						
						if (webOrNot == 0) {
							
							TryStuff.ShowPictures(second);    // вывод фоток с диска
						
							continue;
							
						} else {
				
							TryStuff.WebShowPictures(second); // вывод фоток с Инета
							
							continue;
						}
					}
					
					// 3 уровень сортировки: если нашлось больше .. елементов во Втором списке, то Третье окно будет
					
					if (second.size() > 1) {  // встроенное условие для 3 уровня сортировки

					// Выходит окно с 3 stage Подсортировки, thirdNumberArray нужен только для j
					// сейчас используем thirdNumberArray только чтобы вводить номер игры для вывода Фотки
						
						third = TryStuff.sortingListObjedts (second);
										
					if ( TryStuff.j==null || TryStuff.j.trim().isEmpty() ) {
							
						TryStuff.m = 0;   // поиск будет продолжаться дальше
					}
					
					else {
						
						// если при подсортировки вводим Код *_ _ _, то это число пишется в переменную m для вывода Фото игры на экран
						
						if ( nesServiceClass.isStarCodeOk(TryStuff.j) ) {
							
							TryStuff.m = Integer.parseInt(TryStuff.j.substring(1));
							TryStuff.j3 = "";
							TryStuff.j2 = "all Games";

						}
					}
										
					// если выше ввели код *_ _ , то выводится фотки этой Игры (номер которой ввели)
					
					if ((TryStuff.m > 0) && (TryStuff.m <= second.size())) {
						
						// если выше ввели код *_ _ , то выводится фотки этой Игры (номер которой ввели)
						
						nesServiceClass.windowShow (second.get(TryStuff.m-1));
						
						if (webOrNot == 0) {
							
							TryStuff.ShowPicturesTough (second);  // вывод фоток с диска
							
							continue;
							
						} else {
							
							TryStuff.webShowPicturesTough (second);  // вывод фоток с Инета
							
							continue;
						}					
					} 
			
					else {
						TryStuff.t = 0;
						TryStuff.j3 = "";
						TryStuff.j2 = "all Games";
						continue;
					}
					
				}  // это конец If встроенного условия для 2 уровня подсортровки
					
					/* этот кусочек входит в действие если условие Второго if не выполняется, т.е. если Игр меньше чем .. */
					
					TryStuff.t = 0;
					TryStuff.j3 = "";
					TryStuff.j2 = "all Games";
					continue;

			}      // это конец If основного условия для 1 уровня подсортровки
				
			/* этот кусочек входит в действие если условие Первго if не выполняется, т.е. если Игр меньше чем .. */
				
				TryStuff.t = 0;
				TryStuff.j3 = "";
				TryStuff.j2 = "all Games";
				continue;

	    };  // конец нашего основног цикла while (начало где-то на строке 114)  
	    
	    nesServiceClass.windowShow ("Конец программы"); // для понимания того, что прога кончилась
	    
	} // конец главного Метода main
	
} // конец Класса MyGameSearcher
