package sang;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
public class enroll extends javax.swing.JFrame {
	 // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration                  
	
	
	static Connection conn = null; // DataBase와 연결을 위한 참조변수 선언.
	static Statement stmt = null; // DataBase에 Query 이용 변수.
	static ResultSet rs = null; // DataBase에 Query 이용 변수.
	static int r = 0;
	static Date date = new Date();
	static Calendar cal = Calendar.getInstance();
	static String dateTime = date.toString();
	BufferedImage image; // 이미지를 저장할 이미지 버퍼.
	static boolean condition = false;
    static boolean Programcondition = false;
	int countNumberPic = 0;

    public enroll() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
                    
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        
        ActionListener click = new click();
        this.setLocation(600, 300);
        setTitle("등록");
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        /*
         * 등록 창에서 입력 값을 받기 위해 넣어주는 텍스트.
         */
        jLabel1.setFont(new java.awt.Font("굴림", 1, 24)); // NOI18N
        jLabel1.setText("등록");
        jButton1.setText("사진촬영");
        jButton1.addActionListener(click);
        jButton2.setText("저장");
		jButton2.addActionListener(click);
        jLabel2.setText("Name");
        jLabel3.setText("Room Number");
        jLabel4.setText("Phone Number");
        jLabel5.setText("Date");
        
        /*
         * 윈도창에서 닫기 버튼 누를때 실행되는 구문.
         */
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
            	if(!Programcondition)
            	{
            		File imgfile = new File("C:\\Users\\ice305\\Desktop\\Customerphotos\\");
    				File[] fileList = imgfile.listFiles();
    				String filename = jTextField1.getText()+jTextField2.getText() +countNumberPic+ ".png";
    				System.out.println(countNumberPic);
    				if (fileList.length > 0) {
    					for (int i = 0; i < fileList.length; i++) {
    						if (((fileList[i].getName()).compareTo(filename) == 0)) {
    							fileList[i].delete();
    							 condition = true;
    						}
    					}
    				} else
    					System.out.print("The picture does not exist in the Customerphotos directory.");
            	}
                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    Programcondition = false;
            }
        });
        
        /*
         * 레이아웃 설정.
         */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel2)))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(78, 78, 78))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(124, 124, 124)))
                .addComponent(jButton2)
                .addGap(35, 35, 35))
        );
        pack();
    }                      
    
    /*
     * 클릭시 일어나는 액션 이벤트 클래스.
     */
    class click implements ActionListener { 
		/*
		 * 사용자 정보들 선언
		 */
		String name = new String(); 
		String roomNumber = new String();
		String phoneNumber = new String();
		char Floor;
		String EntranceTime = new String();
		String ExitTime = new String();
		String picture = new String();
		String EnterDate = new String();
		enroll t = new enroll(image); // 캡쳐이미지 객체 생성해준다.
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://ssdb.ccijo8xfuwup.ap-northeast-2.rds.amazonaws.com:3306/SSDB?verifyServerCertificate=false&useSSL=true",
						"sangsu", "tkd1029718"); // DataBase와 연결-> RDS 사용하기.
				
				if (e.getSource() == jButton1) { // 사진 촬영 버튼 누를시.
					if(jTextField1.getText().isEmpty()||jTextField2.getText().isEmpty()||jTextField3.getText().isEmpty()||jTextField4.getText().isEmpty()) //사용자 이름정보를 입력안하고 사진을 찍으려고 할떄!
					{
							JOptionPane.showMessageDialog(null, "사용자 정보를 먼저 작성해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
					}
					else{ //사용자 정보 입력된 후 사진 촬영 실행 될때
						
						picture = new String();
						
						File imgfile = new File("C:\\Users\\ice305\\Desktop\\Customerphotos\\");
	    				File[] fileList = imgfile.listFiles();
	    				
	    				String filename = jTextField1.getText()+jTextField2.getText() +countNumberPic+ ".png";
	    				if (fileList.length > 0) {
	    					for (int i = 0; i < fileList.length; i++) {
	    						if (((fileList[i].getName()).compareTo(filename) == 0)) {
	    							fileList[i].delete();
	    						}
	    					}
	    				} else
	    					System.out.print("The picture does not exist in the Customerphotos directory.");
						
	    				countNumberPic++;
	    				
						while (picture.isEmpty() == true) {
							picture = PictureRun(jTextField1.getText(),jTextField2.getText()+countNumberPic); // 사진을 찍고 < return : 사진파일경로 >
							System.out.println(picture);
						}
						JOptionPane.showMessageDialog(null, "사진촬영이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
						
			            ImageIcon pic = new ImageIcon(picture);
			            jLabel6.setIcon(pic);
						condition = true;
					}
				} else if (e.getSource() == jButton2) { //사용자 정보 입력구문
					
					name = String.valueOf(jTextField1.getText()); // 이름
					roomNumber = String.valueOf(jTextField2.getText()); // 호실
					phoneNumber = String.valueOf(jTextField3.getText()); // 휴대번호
					EnterDate = String.valueOf(jTextField4.getText()); //몇일 머무를지

					if (name.isEmpty() || roomNumber.isEmpty() || phoneNumber.isEmpty() || EnterDate.isEmpty()) {  //공백이 있는경우.
						JOptionPane.showMessageDialog(null, "입력되지 않은 내용이 있습니다.", "알림", JOptionPane.WARNING_MESSAGE);
					}
					if (!name.isEmpty() && !roomNumber.isEmpty() && !phoneNumber.isEmpty() && picture.isEmpty()&& !EnterDate.isEmpty()) {  //사진촬영을 안한경우.
						JOptionPane.showMessageDialog(null, "사용자에 사진을 입력해야 합니다.", "알림", JOptionPane.WARNING_MESSAGE);
					}
					if (!name.equals("") && !roomNumber.equals("") && !phoneNumber.equals("") && !picture.equals("")&& !EnterDate.equals("")) { //사용자 입력 값을 모두 입력 했을 시 실행 구문.

						Floor = roomNumber.charAt(0); // 층수
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, Integer.parseInt(EnterDate));
						
						renameFile("C:\\Users\\ice305\\Desktop\\Customerphotos\\"+jTextField1.getText()+jTextField2.getText()+countNumberPic+ ".png","C:\\Users\\ice305\\Desktop\\Customerphotos\\"+jTextField1.getText()+jTextField2.getText() + ".png");
						
						
						try {
							stmt = (Statement) conn.createStatement();
							r = stmt.executeUpdate(
									"insert into info " + "(`name`,`phoneNumber`,`roomNumber`,`EntranceTime`,`ExitTime`,`picture`,`level`) value ('" + name+roomNumber
											+ "','" + phoneNumber + "','" + roomNumber + "','" + dateTime + "','" + cal.getTime() + "','" + picture.substring(0, picture.length()-5)+picture.substring(picture.length()-4, picture.length()) +"','"+Floor+ "')");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (r == 1) {
							System.out.println("You have successfully entered a post.");
						} else {
							System.out.println("Input Failure.");
						}
						System.out.println("");

						JOptionPane.showMessageDialog(null, "저장되었습니다.","완료", JOptionPane.INFORMATION_MESSAGE);
						Programcondition = true;
					}
				}
			} catch (ClassNotFoundException cnfe) {
				System.out.println("The class could not be found." + cnfe.getMessage());
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}

	}
    
    
    public void renameFile(String filename, String newFilename) {
        File file = new File( filename );
        File fileNew = new File( newFilename );
        if( file.exists() ) file.renameTo( fileNew );
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(enroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enroll().setVisible(true);
            }
        });
    }
    
    /*
     * 사진 촬영 및 얼굴 캡쳐 사용 함수.
     */
 	public static String PictureRun(String inputName,String roomNumber) {
 		String result = new String(); // 사진파일을 리턴!!
 		System.loadLibrary("opencv_java2412"); // 라이브러리 로드.
 		enroll t = new enroll(); // 캡쳐이미지 객체 생성해준다.
 		VideoCapture camera = new VideoCapture(1); // 비디오를 캡쳐할 카메라 객체를 생성해준다. (매개변수 : 카메라 ID ->1개면 0으로 설정)
 		Mat frame = new Mat(); // 행렬을 생성해준다
 		CascadeClassifier faceDetector = new CascadeClassifier(
 				"C:\\Users\\ice305\\Downloads\\opencv\\build\\share\\OpenCV\\lbpcascades\\lbpcascade_frontalface.xml");
 		if (!camera.isOpened()) { // 카메라가 닫혀있을때.
 			System.out.println("카메라를 열수 없습니다.");
 		} else {
 			while (true) {
 				if (camera.read(frame)) { // 카메라에서 이미지 데이터를 행렬단위로 읽어 성공하면.
 					BufferedImage image = t.MatToBufferedImage(frame); // 행렬이미지 데이터를 이미지 데이터로.
 					// t.window(image, "찍힌 사진", 300,300);
 					t.grayscale(image);
 					t.saveImage(image);
 					Mat Mat_image = Highgui.imread(".\\result.png");
 					// 이미지에서 얼굴검출
 					MatOfRect faceDetections = new MatOfRect();
 					faceDetector.detectMultiScale(Mat_image, faceDetections);
 					Mat temp = new Mat();
 					// 그림그려준다 네모박스!!
 					for (Rect rect : faceDetections.toArray()) {
 						temp = new Mat(Mat_image, rect);
 						String filename = inputName+roomNumber + ".png";
 						Highgui.imwrite("C:\\Users\\ice305\\Desktop\\Customerphotos\\" + filename, temp);
 						result = "C:\\\\\\\\Users\\\\\\\\ice305\\\\\\\\Desktop\\\\\\\\Customerphotos\\\\\\\\"
 								+ filename;
 					}
 					break;
 				}
 			}
 		}
 		camera.release();
 		return result;
 	}

 	public enroll(BufferedImage img) {
 		image = img;
 	}
 	
 	// 이미지를 윈도우상에 보여준다.
 	public void window(BufferedImage img, String text, int x, int y) {
 		JFrame frame0 = new JFrame();
 		frame0.getContentPane().add(new enroll(img));
 		frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame0.setTitle(text); // tool action bar 이름 정해주는거.
 		frame0.setSize(img.getWidth(), img.getHeight() + 30); // tool 크기.
 		frame0.setLocation(x, y);// 윈도우상에 어디에 위치해주는지.
 		frame0.setVisible(true); // false 해주면 윈도우상에 안나온다.
 	}

 	// Load an image 이미지를 로드한다.
 	public BufferedImage loadImage(String file) {
 		BufferedImage img;
 		try {
 			File input = new File(file);
 			img = ImageIO.read(input);
 			return img;
 		} catch (Exception e) {
 			System.out.println("erro");
 		}
 		return null;
 	}

 	// 이미지 데이터를 window에 저장한다.
 	public void saveImage(BufferedImage img) {
 		try {
 			File outputfile = new File("C:\\Users\\ice305\\workspace\\FaceDetection\\result.png");
 			ImageIO.write(img, "png", outputfile);
 		} catch (Exception e) {
 			System.out.println("error");
 		}
 	}

 	// 회색필터 적용.
 	public BufferedImage grayscale(BufferedImage img) {
 		for (int i = 0; i < img.getHeight(); i++) {
 			for (int j = 0; j < img.getWidth(); j++) {
 				Color c = new Color(img.getRGB(j, i));
 				int red = (int) (c.getRed() * 0.299);
 				int green = (int) (c.getGreen() * 0.587);
 				int blue = (int) (c.getBlue() * 0.114);
 				Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
 				img.setRGB(j, i, newColor.getRGB());
 			}
 		}
 		return img;
 	}

 	// 행렬데이터를 받아서 이미지 데이터형식으로 바꿔주는 함수.
 	public BufferedImage MatToBufferedImage(Mat frame) {
 		// Mat() to BufferedImage
 		int type = 0;
 		if (frame.channels() == 1) {
 			type = BufferedImage.TYPE_BYTE_GRAY;
 		} else if (frame.channels() == 3) {
 			type = BufferedImage.TYPE_3BYTE_BGR;
 		}
 		BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
 		WritableRaster raster = image.getRaster();
 		DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
 		byte[] data = dataBuffer.getData();
 		frame.get(0, 0, data);
 		return image;
 	}
 	

}