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
    
    private ArrayList<ProductPanel> productComponents;
    private databaseConnect databaseConnect;
    private int styleCode;
    private JScrollPane scrollPane;
    private JPanel productPanelContainer;
    
    private int productID;
    private String userID;
    private String loggedInUserID;

    
    
    
//    ImageIcon whiteHeartImg = new ImageIcon("images/whiteHeart.png");
//    Image wImg = whiteHeartImg.getImage();
//    Image whiteHeart = wImg.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
//    ImageIcon whiteHeartIcon = new ImageIcon(whiteHeart);
//    private JButton productWish = new JButton(whiteHeartIcon);
//    
//    ImageIcon redHeartImg = new ImageIcon("images/redHeart.png");

    public void setLoggedInUserID(String userID) {
        this.userID = userID;
        this.loggedInUserID = userID;
        System.out.println("user ID: " + userID);
        System.out.println("loggedInUserID: " + loggedInUserID);
    }
    
    public MainPage(MainUI mainUI) {
        setLayout(null);

        databaseConnect = new databaseConnect();
        
        
        this.userID = loggedInUserID;

        System.out.println("user ID: " + userID);
        System.out.println("loggedInUserID: " + loggedInUserID);
        
        
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

//        productWish.setBounds(141,122,22,22);
//        productWish.setBorderPainted(false);
//        productWish.setContentAreaFilled(false);
//        productWish.setFocusPainted(false);
//        productWish.setIcon(whiteHeartIcon);
//        productWish.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        productWish.setRolloverIcon(redHeartImg);
        
        strCombo.setBounds(843, 141, 70, 22);

        // PriceSl 위치 설정
        PriceSl.setBounds(510, 113, 250, 70);
        
        productPanelContainer = new JPanel();
        productPanelContainer.setLayout(null);

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
        productComponents = new ArrayList<>();
        
        productPanelContainer.setPreferredSize(new Dimension(1000, 2000));
        //productPanelContainer.setBounds(0,200, 1000, 2000);
        productPanelContainer.setBackground(Color.WHITE);
        scrollPane = new JScrollPane(productPanelContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 200, 974, 430);
        add(scrollPane);
        
        vintageB.addMouseListener(new StyleButtonListener(2));  // 2는 vintage 스타일 코드
        modernB.addMouseListener(new StyleButtonListener(1));   // 1은 modern 스타일 코드
        midcenturyB.addMouseListener(new StyleButtonListener(3));  // 3은 midcentury 스타일 코드
        contryB.addMouseListener(new StyleButtonListener(4));    // 4는 country 스타일 코드
        naturalB.addMouseListener(new StyleButtonListener(5)); 

        
        
        

        LogoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainUI.showStartPanel();
            }
        });

        profileB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// profileB 버튼을 누를 때 loggedInUserID 값을 가져와서
                String loggedInUserID = mainUI.getLoggedInUserID();

                // WishListPanel에 해당 값을 전달
                mainUI.getWishListPanel().setLoggedInUserID(loggedInUserID);
            	
                mainUI.showWishListPanel();//
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
        
        
     // 메인페이지가 생성될 때 현재 로그인한 유저의 위시리스트를 조회하여 하트 색상 업데이트
        updateHeartIcons();
        
    }

    public JScrollPane getScrollPane() {	
    	return scrollPane;	}
    	
    private ProductPanel createProductPanel(String product_name, String product_price, String product_img, int product_ID) {
        ImageIcon productImage = new ImageIcon(product_img);
        
        ProductPanel productPanel = new ProductPanel(product_name, String.valueOf(product_price), productImage,product_ID, this.userID);
        
        

        // ProductLabel 내부의 컴포넌트 크기 및 배치 설정
        productPanel.setLayout(null);
        //productPanel.setBounds(200, 220, 45, 239);
        productPanel.setBackground(new Color(139, 158, 211));

        // 이미지 크기 및 위치
        JLabel imageLabel = new JLabel(productImage);
        imageLabel.setBounds(15, 15, 170, 150);

        // 상품 이름 라벨
        JLabel nameLabel = new JLabel(product_name);
        nameLabel.setBounds(15, 170, 170, 20);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 상품 가격 라벨
        JLabel priceLabel = new JLabel(String.valueOf(product_price));
        priceLabel.setBounds(15, 190, 170, 20);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 찜 버튼 추가
//        productWish.setBounds(141,122,22,22);
//        productWish.setBorderPainted(false);
//        productWish.setContentAreaFilled(false);
//        productWish.setFocusPainted(false);
//        productWish.setIcon(whiteHeartIcon);
//        profileB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // ProductLabel에 컴포넌트 추가
        

        //imageLabel.add(productWish);
        productPanel.add(imageLabel);
        productPanel.add(nameLabel);
        productPanel.add(priceLabel);
        //productPanel.add(productWish);
        

        return productPanel;
    }

    //검색 기능
    
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
    
    public static class ProductPanel extends JPanel {

    	private JButton productWish;
        private static final ImageIcon whiteHeartIcon;
        private static final ImageIcon redHeartIcon;
        
        private int productID;  // 상품의 ID를 저장할 변수
        private String userID;  // 사용자의 ID를 저장할 변수
        

        static {
            ImageIcon whiteHeartImg = new ImageIcon("images/whiteHeart.png");
            Image wImg = whiteHeartImg.getImage();
            Image whiteHeart = wImg.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            whiteHeartIcon = new ImageIcon(whiteHeart);

            ImageIcon redHeartImg = new ImageIcon("images/redHeart.png");
            Image rImg = redHeartImg.getImage();
            Image redHeart = rImg.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            redHeartIcon = new ImageIcon(redHeart);
        }
        
        public ProductPanel(String name, String price, Icon image, int productID, String userID) {
            this.productID = productID;
            this.userID = userID;
            
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 세로로 나열되도록 설정
            productWish = new JButton(whiteHeartIcon);
            
         // ProductPanel에 사용될 버튼 정의
            productWish = new JButton(whiteHeartIcon);
            
            ImageIcon redHeartImg = new ImageIcon("images/redHeart.png");

            JLabel nameLabel = new JLabel(name);
            JLabel priceLabel = new JLabel(price);
            JLabel imageLabel = new JLabel(image);
            
            productWish.setBounds(156,137,22,22);
            productWish.setBorderPainted(false);
            productWish.setContentAreaFilled(false);
            productWish.setFocusPainted(false);
            productWish.setIcon(whiteHeartIcon);
            productWish.setCursor(new Cursor(Cursor.HAND_CURSOR));
            productWish.setPressedIcon(redHeartImg);
            productWish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleHeartIcon();
                }
            });


            add(nameLabel);
            add(priceLabel);
            add(imageLabel);
            add(productWish);
        }
        
        public int getProductID() {
            return productID;
        }

        public String getUserID() {
            return userID;
        }
        
        private void toggleHeartIcon() {
        	
        	int productID = getProductID();
            String userID = getUserID();
            System.out.println("토글/productID: "+productID);
            System.out.println("토글/userID: "+userID);
            
            
            if (productWish.getIcon().equals(whiteHeartIcon)) {
                productWish.setIcon(redHeartIcon);
                addToWishlist();
            } else {
                productWish.setIcon(whiteHeartIcon);
                removeFromWishlist();
            }
        }

        private void addToWishlist() {
            try {
                if (this.userID != null) {
                    databaseConnect dbConnect = new databaseConnect();
                    dbConnect.addToWishlist(userID, productID); // 여기서 확인
                } else {
                    System.err.println("userID가 null입니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void removeFromWishlist() {
            databaseConnect dbConnect = new databaseConnect();
            try {
                dbConnect.removeFromWishlist(userID, productID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        public void setWishlistStatus(boolean isInWishlist) {
            if (isInWishlist) {
                productWish.setIcon(redHeartIcon);
            } else {
                productWish.setIcon(whiteHeartIcon);
            }
        }
        
        
    }
    
    
    private class StyleButtonListener extends MouseAdapter {
    	private int styleCode;

        public StyleButtonListener(int styleCode) {
            this.styleCode = styleCode;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        	setStyleCode(styleCode);
            filterProductsByStyle(styleCode);
        }
    }
    private void setStyleCode(int styleCode) {
        this.styleCode = styleCode;
    }
    
    
    public void filterProductsByStyle(int styleCode) {
    	ResultSet resultSet = null;
        try {
            resultSet = databaseConnect.getProducts(styleCode);
            if (resultSet != null && !resultSet.isClosed()) {
                updateProductComponents(resultSet);
            } else {
                System.out.println("ResultSet is null or closed.");
            }
                       
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
        	databaseConnect.close(null, null, resultSet);
        }
        
    }
    private void updateProductComponents(ResultSet resultSet) {
    	 try {
    	        // resultSet이 닫혀 있는지 확인
    	        if (resultSet.isClosed()) {
    	            System.out.println("ResultSet is closed.");
    	            return;
    	        }

    	        // 기존 상품 컴포넌트 제거
    	        for (ProductPanel productPanel : productComponents) {
    	            remove(productPanel);
    	        }
    	        
    	        productPanelContainer.removeAll();
    	        productComponents.clear();
    	        //addProductComponents();
    	        int productsPerRow = 4;
    	        int xInitial = 45;
    	        int yInitial = 20;
    	        int xGap = 25;
    	        int yGap = 40;

    	        int i = 0;
    	        while (resultSet.next()) {
    	        	String product_name = resultSet.getString("product_name");
    	            String product_price = resultSet.getString("product_price");
    	            String product_img = resultSet.getString("product_img");
    	            int product_ID= resultSet.getInt("product_ID");

    	            ProductPanel productPanel = createProductPanel(product_name, product_price, product_img, product_ID);
    	            
    	            
    	         // 위시리스트에 해당 상품이 있는지 확인하고, 상태를 ProductPanel에 전달
    	            boolean isInWishlist = checkIfInWishlist(userID, product_ID);
    	            productPanel.setWishlistStatus(isInWishlist);
    	            
    	            int row = i / productsPerRow;
    	            int col = i % productsPerRow;

    	            int x = xInitial + col * (200 + xGap);
    	            int y = yInitial + row * (220 + yGap);

    	            productPanel.setBounds(x, y, 200, 220);
    	            productComponents.add(productPanel);
    	            productPanelContainer.add(productPanel);
    	            
    	            i++;
    	        }
    	        resultSet.close();

    	        // UI 다시 그리기
    	        revalidate();
    	        repaint();
    	        
    	 } catch (SQLException e) {
    		    System.err.println("SQL Exception: " + e.getMessage());
    		    e.printStackTrace();
    		} catch (Exception e) {
    		    System.err.println("Exception: " + e.getMessage());
    		    e.printStackTrace();
    		}

    	}
    
 // 위시리스트에 있는 상품들의 하트 색상을 업데이트하는 메서드
    private void updateHeartIcons() {
        ResultSet wishlistProductsResultSet = null;

        try {
            // 현재 로그인한 유저의 위시리스트에 있는 상품들 조회
            wishlistProductsResultSet = databaseConnect.getWishlistProducts(loggedInUserID);

            if (wishlistProductsResultSet != null) {
                while (wishlistProductsResultSet.next()) {
                    int productID = wishlistProductsResultSet.getInt("product_ID");

                    // 각 상품에 대한 하트 컴포넌트를 찾아서 Wishlist 상태를 업데이트
                    updateHeartColor(productID, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnect.close(null, null, wishlistProductsResultSet);
        }
    }

    // 특정 상품의 하트 색상을 붉은 색으로 업데이트하는 메서드
    private void updateHeartColor(int productID, boolean isInWishlist) {
        for (ProductPanel productPanel : productComponents) {
            if (productPanel.getProductID() == productID) {
                productPanel.setWishlistStatus(isInWishlist);
                break; // 해당 상품을 찾았으면 반복문 종료
            }
        }
    }

    
    private boolean checkIfInWishlist(String userID, int productID) {
        try {
            // checkIfInWishlist가 databaseConnect 클래스의 메소드라고 가정합니다.
            return databaseConnect.checkIfInWishlist(userID, productID);
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 또는 예외를 처리하는 방식에 따라 다르게 처리하세요.
        }
    }
    
    
}
