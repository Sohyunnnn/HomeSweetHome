package HomeSweetHome;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class MainPage extends JPanel {
    private JLabel vintageB = new JLabel("빈티지");
    private JLabel modernB = new JLabel("모던");
    private JLabel midcenturyB = new JLabel("미드센추리");
    private JLabel contryB = new JLabel("컨트리");
    private JLabel naturalB = new JLabel("내추럴");

    private JLabel lampB = new JLabel("조명");
    private JLabel chairB = new JLabel("탁상");
    private JLabel bedB = new JLabel("침대");
    private JLabel sofaB = new JLabel("소파");
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
    
    private String userID;
    private String loggedInUserID;

    private int currentStyleCode;
    private int currentfurnitureType;
    private int maxprice;
    private int minprice;
    
    
    public void setLoggedInUserID(String userID) {
        this.userID = userID;
        this.loggedInUserID = userID;
    }
    
    public MainPage(MainUI mainUI) {
        setLayout(null);

        databaseConnect = new databaseConnect();
        System.out.println("메인 실행");
        
        
        this.userID = loggedInUserID;

        
        
        vintageB.setBounds(103, 84, 93, 24);
        modernB.setBounds(277, 84, 93, 24);
        midcenturyB.setBounds(438, 84, 93, 24);
        contryB.setBounds(650, 84, 93, 24);
        naturalB.setBounds(827, 84, 93, 24);

        lampB.setBounds(357, 138, 93, 24);
        chairB.setBounds(169, 138, 93, 24);
        bedB.setBounds(70, 138, 93, 24);
        sofaB.setBounds(263, 138, 93, 24);

        LogoutB.setBounds(773, 22, 95, 32);
        LogoutB.setBackground(Color.decode("#D9D9D9"));

        profileB.setBounds(882, 16, 45, 45);
        profileB.setBorderPainted(false);
        profileB.setContentAreaFilled(false);
        profileB.setFocusPainted(false);             

        profileB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel smallLogoLabel = new JLabel(smallLogo);
        smallLogoLabel.setBounds(16, 16, 262, 39);

       

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
        add(PriceSl);
        add(maxPriceLabel);
        add(minPriceLabel);
        add(strCombo);
        
        
        ImageIcon backButtonImg= new ImageIcon("images/backButton.png");
        JLabel backButton = new JLabel(backButtonImg);
        backButton.setBounds(20, 66, 32, 32);
        add(backButton);
        
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	mainUI.showImagePanel();
            }
        });
        
        smallLogoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	mainUI.showWishListPanel();
            }
        });
        

        setBackground(Color.WHITE);
        productComponents = new ArrayList<>();
        
        productPanelContainer.setPreferredSize(new Dimension(1000, 2000));
        productPanelContainer.setBackground(Color.WHITE);
        scrollPane = new JScrollPane(productPanelContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 200, 974, 430);
        add(scrollPane);
        
        vintageB.addMouseListener(new StyleButtonListener(2, 0));  // 2는 vintage 스타일 코드
        modernB.addMouseListener(new StyleButtonListener(1, 0));   // 1은 modern 스타일 코드
        midcenturyB.addMouseListener(new StyleButtonListener(3, 0));  // 3은 midcentury 스타일 코드
        contryB.addMouseListener(new StyleButtonListener(4, 0));    // 4는 country 스타일 코드
        naturalB.addMouseListener(new StyleButtonListener(5, 0)); 

        lampB.addMouseListener(new FurnitureTypeButtonListener(4)); 
        chairB.addMouseListener(new FurnitureTypeButtonListener(2)); 
        bedB.addMouseListener(new FurnitureTypeButtonListener(1)); 
        sofaB.addMouseListener(new FurnitureTypeButtonListener(3));  

        

        LogoutB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시의 동작
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "프로그램을 종료하시겠습니까?",
                        "종료 확인",
                        JOptionPane.YES_NO_OPTION
                );

                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0); // 프로그램 종료
                } else {
                    mainUI.showLogInPanel(); // 로그인 화면으로 전환
                }
            }
        });
        

        profileB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// profileB 버튼을 누를 때 loggedInUserID 값을 가져오기
                String loggedInUserID = mainUI.getLoggedInUserID();

                // WishListPanel에 해당 값을 전달
                mainUI.getWishListPanel().setLoggedInUserID(loggedInUserID);
                
            	
                mainUI.showWishListPanel();
            }
        });


        PriceSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int minPrice = PriceSl.getLowerValue();
                int maxPrice = PriceSl.getUpperValue();
                //최소, 최대값을 DB에서 상품 가져오는 메소드에 넣기.
                setPrice(minPrice, maxPrice);
                maxPriceLabel.setText(String.valueOf(maxPrice));
                minPriceLabel.setText(String.valueOf(minPrice));
                filterProductsByStyle(currentStyleCode, currentfurnitureType, minPrice, maxPrice);
            }
        });
        
        
        
     // 메인페이지가 생성될 때 현재 로그인한 유저의 위시리스트를 조회하여 하트 색상 업데이트
        updateHeartIcons();
        
    }
    private void setPrice(int minPrice, int maxPrice) {
    	this.maxprice = maxPrice;
    	this.minprice = minPrice;
    	System.out.println("Min Price: " + minprice + ", Max Price: " + maxprice); // 디버깅을 위한 출력 추가
        
    }


    public JScrollPane getScrollPane() {	
    	return scrollPane;	
    	}
    	
    public ProductPanel createProductPanel(String product_name, String product_price, String product_img, int product_ID) {
        ImageIcon productImage = new ImageIcon(product_img);
        
        ProductPanel productPanel = new ProductPanel(product_name, String.valueOf(product_price), productImage,product_ID, this.userID);
        
        

        // ProductLabel 내부의 컴포넌트 크기 및 배치 설정
        productPanel.setLayout(null);
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


        
        // ProductLabel에 컴포넌트 추가    

        productPanel.add(imageLabel);
        productPanel.add(nameLabel);
        productPanel.add(priceLabel);
        
        
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // 클릭한 이미지의 product_ID를 가져와서 활용
                    int clickedProductID = productPanel.getProductID();

                    // productID를 clickedProductID로 수정
                    String imageURL = databaseConnect.getImageUrl(String.valueOf(clickedProductID));

                    // 이미지 URL을 웹페이지 창으로 열기
                    openWebpage(imageURL);

                    System.out.println("상품 ID가 " + clickedProductID + "인 제품 이미지를 클릭했습니다.");
                    System.out.println("이미지 URL: " + imageURL);
                } catch (Exception ex) {
                    // 예외 처리
                    System.err.println("예외: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        

        return productPanel;
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
        private int furnitureType;

         public StyleButtonListener(int styleCode, int furnitureType) {
        	
        	 this.styleCode = styleCode;
             this.furnitureType = furnitureType;
         }

         @Override
         public void mouseClicked(MouseEvent e) {
            currentStyleCode = styleCode;
            setPrice(0,0);
            filterProductsByStyle(currentStyleCode, furnitureType, minprice, maxprice);
         }
     }
     private void setStyleCode(int styleCode) {
         this.currentStyleCode = styleCode;
     }
     
     class FurnitureTypeButtonListener extends MouseAdapter {
         private int furnitureType;

         public FurnitureTypeButtonListener(int furnitureType) {
             this.furnitureType = furnitureType;
         }

         @Override
         public void mouseClicked(MouseEvent e) {
        	 currentfurnitureType = furnitureType;
        	 setPrice(0,0);
             filterProductsByStyle(currentStyleCode, furnitureType, minprice, maxprice);
         }
     }

    
    public void filterProductsByStyle(int styleCode, int furnitureType, int minPrice, int maxPrice) {
    	ResultSet resultSet = null;
        try {
        	System.out.println("Filtering products...");
        	 System.out.println("스타일코드"+styleCode+" F_type"+furnitureType);
        	 System.out.println("Min Price: " + minPrice + ", Max Price: " + maxPrice); // 디버깅을 위한 출력 추가
        	 if (resultSet != null && !resultSet.isClosed()) {
                 resultSet.close();
             }
             
             resultSet = databaseConnect.getProducts(styleCode, furnitureType, minPrice, maxPrice);       
             
             if (resultSet != null && !resultSet.isClosed()) {
             	System.out.println("Updating product components...");
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
    	        //resultSet.close();

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
            return databaseConnect.checkIfInWishlist(userID, productID);
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    private static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
    
    
    
