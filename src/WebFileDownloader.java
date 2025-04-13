
/* Класс с 3-мя Методами:
 * Первый - может считывать с Git Hub инфу по играм, создавать в папке
 * текстовый документ и писать туда эту инфу, а потом уже прога читает инфу из этого файла
 * - сейчас не использую
 * Второй - проверяет есть ли Интернет, применяется в главном Методе (Класс MyGameSearcher)
 * Третий - проверяет доступна ли фотография в папке на Git Hub и если доступна, то читает
 * эту фотографию и выводит на экран, применяется в Методе № 26 WebShowPictures в классе TrySutff */

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class WebFileDownloader { // начало Класса
	
	/* заведем Список Строк, куда будем писать строки считываемые из файла на Репозатории */
	
	public static ArrayList <String> myList = new ArrayList <String>();

	/* 1.0 этот метод будет считывать инфу из моего текстового файла хранящегося на GitHub и заводить в моей папке текстовый
	 * файл (если его нету, а если есть, перезаписывать его) со всей скачанной инфой, т.е. он ничего не принимает и
	 * не возвращает, он Обновляет Текстовый Файл проекта (или создает его, если его вообще не было) */
	
	public static void fileFromWebCreator () {
			
		// адрес моего файла на GitHub
		
		String fileUrl = TryStuff.webFileAddres;

		try {
			
			// Создаем URL объект используя мой адрес
			
			URL url = new URL(fileUrl);

			// Открываем соединение к URL
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET"); // Set the request method to GET

			// Check if the response code is HTTP_OK (200)
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				
				// Create a BufferedReader to read the input stream
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;

				// Читаем файл строка за строкой и добавляем строки в наш Список
				
				while ((line = in.readLine()) != null) {
					
					myList.add(line); 
				}

				// Close the BufferedReader
				in.close();
				
			} else {
				
				System.out.println("Failed to fetch the file: " + connection.getResponseCode());
			}
			

			// Disconnect the connection
			connection.disconnect();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		/* Обработаем тут наш Список, для записи его в Файл */
		
		String[] myArray = myList.toArray(new String[0]); // переводим в Массив Строк
		
		//Service_Class.windowShow(myArray); // выводим в Окно
		
		File myTextFile = new File (TryStuff.fileAddres); // объект типа  File в папке Проекта
													//на рабочем столе (мой текст. файл)
		
		/* создаем Текстовый Файл на рабочем столе, если он еще не создан -
		 * случай когда файла изначально не было */

		try { // первое try для первого создания файла
			
			/* в нашей Директории (рабочий стол) создаем наш файл. Только не совсем на рабочем.
			 * Он создается в папке MyGameSearcher на рабочем, даже если ее до этого не было  - это над проверить */
			
			if (myTextFile.createNewFile()) { // если файла не было, создаем
				
				System.out.println ("Text file created good");

			} else { // если файл есть, перезаписываем
				
				//System.out.println ("Text file is already exists");
			}
			
		} catch (IOException e) { // конец первого try по созданию первого файла
			
			System.err.println ("Error creating: " + e.getMessage());
		}  
		
		/* если наш Текстовый файл существует, пишем туда инфу (а он должен существовать, т.к.
		 * в предыдущем участке кода мы его создавали) */

		if (myTextFile.exists()) {

			try (BufferedWriter writer = new BufferedWriter (new FileWriter(TryStuff.fileAddres, false))) {
			//try (PrintWriter writer = new PrintWriter(new FileWriter(fileAddres, false))) {
				for (int i=0; i<myArray.length; i++) {
					
					/* записываем в файл весь наш Массив строк, считанных с Репозатория */
					
					writer.write(myArray[i]);
					writer.newLine();
					//writer.write("\n"); // это вставил из-за PrintWriter			
				}
				
				/* это "закрытие" я ввел по аналогии с закрытием потока при чтении
				 * инфы с инета. Я это ввел, и стал работать следующий блок - сейчас
				 * текстовый файл удаляется, но т.к. временного нет, он не переиминовывается */
				
				writer.close(); // придумал сам
				
				//System.out.println ("file filled with info from the Internet");
				nesServiceClass.windowShow ("Текст Файл обновлен.");
				
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
		}
	
	} // конец метода 1.0 fileFromWebCreator
	
	/* 2. мой метод для проверки, есть вообще Инет или нет */
	
	public static boolean isInternetAvailable () { // начало метода isInternetAvailable
		
		try {
			
			URL url = new URL (TryStuff.inetChecking); // передаем адрес Интернет Ресурса
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			//urlConnection.setDefaultRequestProperty("User-Agent", "Test");
			//urlConnection.setDefaultRequestProperty("Connection", "close");
			urlConnection.setConnectTimeout(1000);
			urlConnection.connect();
			
			return (urlConnection.getResponseCode() == 200);
			
		} catch (Exception e) {
			
			return false;
		}		
	} // конец метода 2. isInternetAvailable
	
	/* 3. Метод по проверке работоспособности ссылки на фотку и выводу этой фотки на экран 
	 * используется в методах Класса TryStuff № 26, 27, 28 */
	
	public static void showWebPicture (String ur) {

		try {
			
			URL url = new URL(ur);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000); // Set timeout
			connection.setReadTimeout(5000); // Set timeout

		// Connect to the URL
			
			connection.connect();

		// Check the response code
			
			int responseCode = connection.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) { // если картинка доступна выводим ее
				
				//TryStuff.windowShowString("Input stream can be opened.");
				
				try {
					
					Image image = ImageIO.read(url);
				
					// Create an ImageIcon from the image
					
					ImageIcon imageIcon = new ImageIcon(image);

					// Create a JLabel to hold the ImageIcon
					
					JOptionPane.showMessageDialog(null, imageIcon, "That's the Front Photo of The Game", JOptionPane.PLAIN_MESSAGE);
					
					} catch (MalformedURLException e) {
						
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "shit" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						
					} catch (IOException e) {
						
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "shit" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				
			} else {
				
				nesServiceClass.windowShow ("Cannot open input stream. Response code: " + responseCode);
			}
			
		} catch (IOException e) {
				System.out.println("An error occurred: " + e.getMessage());
		}
		
	}  // конец метода 3. shotWebPicture ()
		
} // конец Класса
