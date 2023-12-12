package HomeSweetHome;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPage extends JPanel {
    private JLabel vintageB = new JLabel("빈티지");
    private JLabel modernB = new JLabel("모던");
    private JLabel midcenturyB = new JLabel("미드센추리");
    private JLabel contryB = new JLabel("컨트리");
    private JLabel naturalB = new JLabel("내추럴");

    private JLabel lampB = new JLabel("조명");
    private JLabel chairB = new JLabel("의자");
    private JLabel bedB = new JLabel("침대");
    private JLabel sofaB = new JLabel("소파");
    private JTextField Mappingtf = new Mappingtf("검색");
    private CustomSlider PriceSl = new CustomSlider();
    private String[] sort = {"최신순", "인기순", "높은가격순", "낮은가격순"};
    private JComboBox<String> strCombo = new JComboBox<String>(sort);

    ImageIcon profileImg = new ImageIcon("images/profile.png");
    Image pImg = profileImg.getImage();
    Image updateImg = pImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
    ImageIcon updateIcon = new ImageIcon(updateImg);
    ImageIcon smallLogo = new ImageIcon("images/smallLogo.png");
    private JButton profileB = new JButton(updateIcon);
    private RoundedButton LogoutB = new RoundedButton("로그아웃");
    private JLabel maxPriceLabel = new JLabel("1000000");
    private JLabel minPriceLabel = new JLabel("1000");
    
//    private ArrayList<ProductLabel> productComponents;
    private databaseConnect databaseConnect;
//    private int styleCode;

    public MainPage(MainUI mainUI) {
        setLayout(null);

        databaseConnect = new databaseConnect();
        
        vintageB.setBounds(103, 84, 93, 24);
        modernB.setBounds(277, 84, 93, 24);
        midcenturyB.setBounds(438, 84, 93, 24);
        contryB.setBounds(650, 84, 93, 24);
        naturalB.setBounds(827, 84, 93, 24);

        lampB.setBounds(357, 138, 93, 24);
        chairB.setBounds(169, 138, 93, 24);
        bedB.setBounds(70, 138, 93, 24);
        sofaB.setBounds(263, 138, 93, 24);
        Mappingtf.setBounds(311, 25, 428, 28);

        LogoutB.setBounds(773, 22, 95, 32);
        LogoutB.setBackground(Color.decode("#D9D9D9"));

        profileB.setBounds(882, 16, 45, 45);
        profileB.setBorderPainted(false);
        profileB.setContentAreaFilled(false);
        profileB.setFocusPainted(false);

        profileB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel smallLogoLabel = new JLabel(smallLogo);
        smallLogoLabel.setBounds(16, 16, 262, 39);

        strCombo.setBounds(843, 141, 70, 22);

        // PriceSl 위치 설정
        PriceSl.setBounds(510, 113, 250, 70);

        add(vintageB);
        add(modernB);
        add(midcenturyB);
        add(contryB);
        add(naturalB);

        add(lampB);
        add(chairB);
        add(bedB);
        add(sofaB);

        add(profileB);

        add(LogoutB);
        add(smallLogoLabel);
        add(Mappingtf);
        add(PriceSl);
        add(maxPriceLabel);
        add(minPriceLabel);
        add(strCombo);

        setBackground(Color.WHITE);
//        productComponents = new ArrayList<>();
//        
//        
//        vintageB.addMouseListener(new StyleButtonListener(2));  // 1은 vintage 스타일 코드
//        modernB.addMouseListener(new StyleButtonListener(1));   // 2는 modern 스타일 코드
//        midcenturyB.addMouseListener(new StyleButtonListener(3));  // 3은 midcentury 스타일 코드
//        contryB.addMouseListener(new StyleButtonListener(4));    // 4는 country 스타일 코드
//        naturalB.addMouseListener(new StyleButtonListener(5)); 

        // 상품 컴포넌트를 추가하는 메서드 호출
       // addProductComponents();
        
        
        

        LogoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showStartPanel();
            }
        });

        profileB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showWishListPanel();
            }
        });

        strCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 콤보박스 눌렀을 때 리스너 구현
            }
        });

        PriceSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int minPrice = PriceSl.getLowerValue();
                int maxPrice = PriceSl.getUpperValue();
                maxPriceLabel.setText(String.valueOf(maxPrice));
                minPriceLabel.setText(String.valueOf(minPrice));
            }
        });
        
        
    }
//    private void addProductComponents() {
//    	try {
//            ResultSet resultSet = databaseConnect.getProducts(styleCode);
//            while (resultSet.next()) {
//            	
//                int productsPerRow = 4;
//                int xInitial = 45;
//                int yInitial = 260;
//                int xGap = 15;
//                int yGap = 40;
//
//                int i = 0;
//                while (i < 117 && resultSet.next()) {
//                	String product_name = resultSet.getString("product_name");
//                    String product_price = resultSet.getString("product_price");
//                    String product_img = resultSet.getString("product_img");
//
//                    ProductLabel productLabel = createProductLabel(product_name, product_price, product_img);
//
//                	
//                    int row = i / productsPerRow;
//                    int col = i % productsPerRow;
//
//                    int x = xInitial + col * (200 + xGap);
//                    int y = yInitial + row * (220 + yGap);
//
//                    productLabel.setBounds(x, y, 200, 220);
//                    productComponents.add(productLabel);
//                    add(productLabel);
//                    
//                    i++;
//                }
//                // 데이터 검색이 완료된 후 ResultSet을 닫음
//                //resultSet.close();
//
//                // UI 다시 그리기
//                revalidate();
//                repaint();
//            }
//    	} catch (SQLException e) {
//    	    System.err.println("SQL Exception: " + e.getMessage());
//    	    e.printStackTrace();
//    	} catch (Exception e) {
//    	    System.err.println("Exception: " + e.getMessage());
//    	    e.printStackTrace();
//    	}
//    }
//    
//    	
//    private ProductLabel createProductLabel(String product_name, String product_price, String product_img) {
//        ImageIcon productImage = new ImageIcon(product_img);
//        
//        ProductLabel productLabel = new ProductLabel(product_name, String.valueOf(product_price), productImage);
//
//        // ProductLabel 내부의 컴포넌트 크기 및 배치 설정
//        productLabel.setLayout(null);
//        
//        productLabel.setBackground(new Color(139, 158, 211));
//
//        // 이미지 크기 및 위치
//        JLabel imageLabel = new JLabel(productImage);
//        imageLabel.setBounds(15, 15, 170, 150);
//
//        // 상품 이름 라벨
//        JLabel nameLabel = new JLabel(product_name);
//        nameLabel.setBounds(15, 170, 170, 20);
//        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        // 상품 가격 라벨
//        JLabel priceLabel = new JLabel(String.valueOf(product_price));
//        priceLabel.setBounds(15, 190, 170, 20);
//        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        // ProductLabel에 컴포넌트 추가
//        productLabel.add(imageLabel);
//        productLabel.add(nameLabel);
//        productLabel.add(priceLabel);
//
//        return productLabel;
//    }

    class Mappingtf extends JTextField {
        private String mapword;

        public Mappingtf(String mapword) {
            this.mapword = mapword;
            setForeground(Color.GRAY);
            setText(mapword);
            setFont(new Font("굴림체", Font.PLAIN, 12));

            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(mapword)) {
                        setText("");
                        setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(mapword);
                        setForeground(Color.GRAY);
                    }
                }
            });
        }
    }
    
//    public static class ProductLabel extends JLabel {
//        public ProductLabel(String name, String price, Icon image) {
//            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 세로로 나열되도록 설정
//
//            JLabel nameLabel = new JLabel(name);
//            JLabel priceLabel = new JLabel(price);
//            JLabel imageLabel = new JLabel(image);
//
//            add(nameLabel);
//            add(priceLabel);
//            add(imageLabel);
//        }
//    }
//    
//    
//    private class StyleButtonListener extends MouseAdapter {
//    	private int styleCode;
//
//        public StyleButtonListener(int styleCode) {
//            this.styleCode = styleCode;
//        }
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//        	setStyleCode(styleCode);
//            filterProductsByStyle(styleCode);
//        }
//    }
//    private void setStyleCode(int styleCode) {
//        this.styleCode = styleCode;
//    }
//    
//    
//    public void filterProductsByStyle(int styleCode) {
//    	ResultSet resultSet = null;
//        try {
//            resultSet = databaseConnect.getProducts(styleCode);
//            if (resultSet != null && !resultSet.isClosed()) {
//                updateProductComponents(resultSet);
//            } else {
//                System.out.println("ResultSet is null or closed.");
//            }
//            resultSet.close();
//            
//        } catch (SQLException e) {
//            System.err.println("SQL Exception: " + e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.err.println("Exception: " + e.getMessage());
//            e.printStackTrace();
//        }
//        
//    }
//    private void updateProductComponents(ResultSet resultSet) {
//    	 try {
//    	        // resultSet이 닫혀 있는지 확인
//    	        if (resultSet.isClosed()) {
//    	            System.out.println("ResultSet is closed.");
//    	            return;
//    	        }
//
//    	        // 기존 상품 컴포넌트 제거
//    	        for (ProductLabel productLabel : productComponents) {
//    	            remove(productLabel);
//    	        }
//    	        productComponents.clear();
//
//    	        int productsPerRow = 4;
//    	        int xInitial = 45;
//    	        int yInitial = 260;
//    	        int xGap = 15;
//    	        int yGap = 40;
//
//    	        int i = 0;
//    	        while (resultSet.next()) {
//    	        	String product_name = resultSet.getString("product_name");
//    	            String product_price = resultSet.getString("product_price");
//    	            String product_img = resultSet.getString("product_img");
//
//    	            ProductLabel productLabel = createProductLabel(product_name, product_price, product_img);
//
//    	            int row = i / productsPerRow;
//    	            int col = i % productsPerRow;
//
//    	            int x = xInitial + col * (200 + xGap);
//    	            int y = yInitial + row * (220 + yGap);
//
//    	            productLabel.setBounds(x, y, 200, 220);
//    	            productComponents.add(productLabel);
//    	            add(productLabel);
//    	            
//    	            i++;
//    	        }
//    	        
//
//    	        // UI 다시 그리기
//    	        revalidate();
//    	        repaint();
//    	        
//    	        
//    	        //databaseConnect.close(null, null, resultSet);
//    	 } catch (SQLException e) {
//    		    System.err.println("SQL Exception: " + e.getMessage());
//    		    e.printStackTrace();
//    		} catch (Exception e) {
//    		    System.err.println("Exception: " + e.getMessage());
//    		    e.printStackTrace();
//    		}
////    		 } finally {
////    		        // 사용이 끝난 후 ResultSet을 닫아줍니다.
////    		        databaseConnect.close(null, null, resultSet);
////    		}
//    	}
}
