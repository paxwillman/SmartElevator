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
	
	
	static Connection conn = null; // DataBase�� ������ ���� �������� ����.
	static Statement stmt = null; // DataBase�� Query �̿� ����.
	static ResultSet rs = null; // DataBase�� Query �̿� ����.
	static int r = 0;
	static Date date = new Date();
	static Calendar cal = Calendar.getInstance();
	static String dateTime = date.toString();
	BufferedImage image; // �̹����� ������ �̹��� ����.
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
        setTitle("���");
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        /*
         * ��� â���� �Է� ���� �ޱ� ���� �־��ִ� �ؽ�Ʈ.
         */
        jLabel1.setFont(new java.awt.Font("����", 1, 24)); // NOI18N
        jLabel1.setText("���");
        jButton1.setText("�����Կ�");
        jButton1.addActionListener(click);
        jButton2.setText("����");
		jButton2.addActionListener(click);
        jLabel2.setText("Name");
        jLabel3.setText("Room Number");
        jLabel4.setText("Phone Number");
        jLabel5.setText("Date");
        
        /*
         * ����â���� �ݱ� ��ư ������ ����Ǵ� ����.
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
         * ���̾ƿ� ����.
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
     * Ŭ���� �Ͼ�� �׼� �̺�Ʈ Ŭ����.
     */
    class click implements ActionListener { 
		/*
		 * ����� ������ ����
		 */
		String name = new String(); 
		String roomNumber = new String();
		String phoneNumber = new String();
		char Floor;
		String EntranceTime = new String();
		String ExitTime = new String();
		String picture = new String();
		String EnterDate = new String();
		enroll t = new enroll(image); // ĸ���̹��� ��ü �������ش�.
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://ssdb.ccijo8xfuwup.ap-northeast-2.rds.amazonaws.com:3306/SSDB?verifyServerCertificate=false&useSSL=true",
						"sangsu", "tkd1029718"); // DataBase�� ����-> RDS ����ϱ�.
				
				if (e.getSource() == jButton1) { // ���� �Կ� ��ư ������.
					if(jTextField1.getText().isEmpty()||jTextField2.getText().isEmpty()||jTextField3.getText().isEmpty()||jTextField4.getText().isEmpty()) //����� �̸������� �Է¾��ϰ� ������ �������� �ҋ�!
					{
							JOptionPane.showMessageDialog(null, "����� ������ ���� �ۼ����ּ���.", "�˸�", JOptionPane.WARNING_MESSAGE);
					}
					else{ //����� ���� �Էµ� �� ���� �Կ� ���� �ɶ�
						
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
							picture = PictureRun(jTextField1.getText(),jTextField2.getText()+countNumberPic); // ������ ��� < return : �������ϰ�� >
							System.out.println(picture);
						}
						JOptionPane.showMessageDialog(null, "�����Կ��� �Ϸ�Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
						
			            ImageIcon pic = new ImageIcon(picture);
			            jLabel6.setIcon(pic);
						condition = true;
					}
				} else if (e.getSource() == jButton2) { //����� ���� �Է±���
					
					name = String.valueOf(jTextField1.getText()); // �̸�
					roomNumber = String.valueOf(jTextField2.getText()); // ȣ��
					phoneNumber = String.valueOf(jTextField3.getText()); // �޴��ȣ
					EnterDate = String.valueOf(jTextField4.getText()); //���� �ӹ�����

					if (name.isEmpty() || roomNumber.isEmpty() || phoneNumber.isEmpty() || EnterDate.isEmpty()) {  //������ �ִ°��.
						JOptionPane.showMessageDialog(null, "�Էµ��� ���� ������ �ֽ��ϴ�.", "�˸�", JOptionPane.WARNING_MESSAGE);
					}
					if (!name.isEmpty() && !roomNumber.isEmpty() && !phoneNumber.isEmpty() && picture.isEmpty()&& !EnterDate.isEmpty()) {  //�����Կ��� ���Ѱ��.
						JOptionPane.showMessageDialog(null, "����ڿ� ������ �Է��ؾ� �մϴ�.", "�˸�", JOptionPane.WARNING_MESSAGE);
					}
					if (!name.equals("") && !roomNumber.equals("") && !phoneNumber.equals("") && !picture.equals("")&& !EnterDate.equals("")) { //����� �Է� ���� ��� �Է� ���� �� ���� ����.

						Floor = roomNumber.charAt(0); // ����
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

						JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.","�Ϸ�", JOptionPane.INFORMATION_MESSAGE);
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
     * ���� �Կ� �� �� ĸ�� ��� �Լ�.
     */
 	public static String PictureRun(String inputName,String roomNumber) {
 		String result = new String(); // ���������� ����!!
 		System.loadLibrary("opencv_java2412"); // ���̺귯�� �ε�.
 		enroll t = new enroll(); // ĸ���̹��� ��ü �������ش�.
 		VideoCapture camera = new VideoCapture(1); // ������ ĸ���� ī�޶� ��ü�� �������ش�. (�Ű����� : ī�޶� ID ->1���� 0���� ����)
 		Mat frame = new Mat(); // ����� �������ش�
 		CascadeClassifier faceDetector = new CascadeClassifier(
 				"C:\\Users\\ice305\\Downloads\\opencv\\build\\share\\OpenCV\\lbpcascades\\lbpcascade_frontalface.xml");
 		if (!camera.isOpened()) { // ī�޶� ����������.
 			System.out.println("ī�޶� ���� �����ϴ�.");
 		} else {
 			while (true) {
 				if (camera.read(frame)) { // ī�޶󿡼� �̹��� �����͸� ��Ĵ����� �о� �����ϸ�.
 					BufferedImage image = t.MatToBufferedImage(frame); // ����̹��� �����͸� �̹��� �����ͷ�.
 					// t.window(image, "���� ����", 300,300);
 					t.grayscale(image);
 					t.saveImage(image);
 					Mat Mat_image = Highgui.imread(".\\result.png");
 					// �̹������� �󱼰���
 					MatOfRect faceDetections = new MatOfRect();
 					faceDetector.detectMultiScale(Mat_image, faceDetections);
 					Mat temp = new Mat();
 					// �׸��׷��ش� �׸�ڽ�!!
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
 	
 	// �̹����� ������� �����ش�.
 	public void window(BufferedImage img, String text, int x, int y) {
 		JFrame frame0 = new JFrame();
 		frame0.getContentPane().add(new enroll(img));
 		frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame0.setTitle(text); // tool action bar �̸� �����ִ°�.
 		frame0.setSize(img.getWidth(), img.getHeight() + 30); // tool ũ��.
 		frame0.setLocation(x, y);// ������� ��� ��ġ���ִ���.
 		frame0.setVisible(true); // false ���ָ� ������� �ȳ��´�.
 	}

 	// Load an image �̹����� �ε��Ѵ�.
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

 	// �̹��� �����͸� window�� �����Ѵ�.
 	public void saveImage(BufferedImage img) {
 		try {
 			File outputfile = new File("C:\\Users\\ice305\\workspace\\FaceDetection\\result.png");
 			ImageIO.write(img, "png", outputfile);
 		} catch (Exception e) {
 			System.out.println("error");
 		}
 	}

 	// ȸ������ ����.
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

 	// ��ĵ����͸� �޾Ƽ� �̹��� �������������� �ٲ��ִ� �Լ�.
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