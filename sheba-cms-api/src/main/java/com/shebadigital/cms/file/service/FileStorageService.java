package com.shebadigital.cms.file.service;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shebadigital.cms.common.ApplicationUtils;
import com.shebadigital.cms.common.Folder;



@Service
public class FileStorageService {
	
	public Logger logger = LoggerFactory.getLogger(FileStorageService.class);
	
	  // *************************** Image upload***********************************
    
	public String uploadImgHeightWidthUnUsed(String folderName, String imageName, MultipartFile uploadFile, int width, int height) {
        String message = "";
        String fileType = uploadFile.getContentType().substring(6, uploadFile.getContentType().length());
        if (fileType.equals("jpeg") || fileType.equals("png") || fileType.equals("jpg")) {
            String filePath = "";
        
            filePath = ApplicationUtils.filePath(folderName) + imageName;
                   
            try {
                InputStream is;
                try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                    is = uploadFile.getInputStream();
                    java.awt.Image img = null;
                    img = ImageIO.read(is);
                    if (folderName == Folder.CMS.name()) {
                        BufferedImage buffImage = (BufferedImage) img;
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(buffImage, "png", os);
                        is = new ByteArrayInputStream(os.toByteArray());
                    } else {
                        BufferedImage buffImage = resizeImage(img, width, height);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(buffImage, "jpg", os);
                        is = new ByteArrayInputStream(os.toByteArray());
                    }

                    int BUFFER_SIZE = 8192;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int a;
                    while (true) {
                        a = is.read(buffer);
                        if (a < 0) {
                            break;
                        }
                        fos.write(buffer, 0, a);
                        fos.flush();
                    }
                }

                is.close();
            } catch (IOException e) {
            }
            message = "Image Uploaded Successfully...";
        } else {
            message = "Format doesn't match...";
        }
        return message;
    }
    
    
    
    
    
    
    public String uploadImgHeightWidth(String folderName, String imageName, String fileType, byte[] fileContent,
			int width, int height) {
		String message = "";

		if (fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("jpg")) {
			String filePath = "";

			filePath = ApplicationUtils.filePath(folderName) + imageName;

			try {
				InputStream is;
				try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
					is = new ByteArrayInputStream(fileContent);
					java.awt.Image img = null;
					img = ImageIO.read(is);
					if (fileType.equalsIgnoreCase("png")) {
						BufferedImage buffImage = (BufferedImage) img;
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						ImageIO.write(buffImage, "png", os);
						is = new ByteArrayInputStream(os.toByteArray());
					} else {
						BufferedImage buffImage = resizeImage(img, width, height);
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						ImageIO.write(buffImage, "jpg", os);
						is = new ByteArrayInputStream(os.toByteArray());
					}

					int BUFFER_SIZE = 8192;
					byte[] buffer = new byte[BUFFER_SIZE];
					int a;
					while (true) {
						a = is.read(buffer);
						if (a < 0) {
							break;
						}
						fos.write(buffer, 0, a);
						fos.flush();
					}
				}

				is.close();
			} catch (IOException e) {
			}

			message = "Image Uploaded Successfully...";

		} else {
			message = "Format doesn't match...";
		}
		return message;
	}
    
    
    public String deleteFile(String folderName, String fileName) {
        
    	String message = "";
 
        String filePath = "";
        
        filePath = ApplicationUtils.filePath(folderName) + fileName;
            	
        File fdelete = new File(filePath);
            
            if (fdelete.exists()) {
                if (fdelete.delete()) {
                	message="File Successfully Deleted";
                } else {
                	message="File not Deleted";
                }
            }
                
      
        return message;
    }
    
    
    public static BufferedImage resizeImage(final java.awt.Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
    
    
    
    // -------------------- Image Read in Byte ---------------------------//
    
    
    
 
    public byte[] fetchImageInBase64Encode(String folderName, String imageName) throws IOException {
        String filePath = "";

        filePath = ApplicationUtils.filePath(folderName) + imageName;
       
        File serverFile = new File(filePath);

        return Base64.getEncoder().encode(Files.readAllBytes(serverFile.toPath()));
    }
    
    
    public byte[] fetchImageInPureByte(String folderName, String imageName) throws Exception {
        
    	String filePath = "";

        filePath = ApplicationUtils.filePath(folderName) + imageName;

        try {
            Path path = new File(filePath).toPath();

            byte[] file = Files.readAllBytes(path);

            return file;

        } catch (Exception e) {

            return new byte[0];
        }
    }
    
    

    

    public String signatureImageUpload(String folderName, String imageName,String fileType, MultipartFile uploadFile) {
    	
    	 String message = "";

         if (fileType.equals("jpeg") || fileType.equals("png") || fileType.equals("jpg")) {
             String filePath = "";
         
             filePath = ApplicationUtils.filePath(folderName) + imageName;
             
             logger.info("++++++++ filePath ++++++ "+filePath);
                    
             try {
                 InputStream is;
                 try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                     is = uploadFile.getInputStream();
                     java.awt.Image img = null;
                     img = ImageIO.read(is);
                     
                     BufferedImage buffImage = (BufferedImage) img;
                     ByteArrayOutputStream os = new ByteArrayOutputStream();
                     ImageIO.write(buffImage, fileType, os);
                     is = new ByteArrayInputStream(os.toByteArray());
                     
                     int BUFFER_SIZE = 8192;
                     byte[] buffer = new byte[BUFFER_SIZE];
                     int a;
                     while (true) {
                         a = is.read(buffer);
                         if (a < 0) {
                             break;
                         }
                         fos.write(buffer, 0, a);
                         fos.flush();
                     }
                 }

                 is.close();
             } catch (IOException e) {
             }
             message = "Image Uploaded Successfully...";
         } else {
             message = "Format doesn't match...";
         }
         
         return message;
    }
    
    
    
    
	public  boolean createWaterMark(String imageName) {
		
		boolean watermarkCreatedStatus = false;

		try {
			BufferedImage image = ImageIO.read(new File(imageName));
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("watermarkimage/semi-trans.png");
			BufferedImage overlay = ImageIO.read(inputStream);	
			
			BufferedImage combined = new BufferedImage(image.getWidth(), image.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(overlay, 0, 0, null);

			String[] fullImageNames = imageName.split(".png");			
			
			File output = new File(fullImageNames[0] + "_" + "watermark.png");
			OutputStream out = new FileOutputStream(output);
			ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(out);
			writer.setOutput(ios);

			ImageWriteParam param = writer.getDefaultWriteParam();
			if (param.canWriteCompressed()) {
				param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				param.setCompressionQuality(0.05f);
			}

			writer.write(null, new IIOImage(combined, null, null), param);
			watermarkCreatedStatus = true;
			ios.close();
			writer.dispose();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return watermarkCreatedStatus;
	}

    
	public  String writeFileToPath(String folderName, byte[] content, String fileName) {

		String message = "";

		String fileFullPath = ApplicationUtils.filePath(folderName) + fileName;

		File file = new File(fileFullPath);
		
		try {
			try (FileOutputStream fos = new FileOutputStream(file)) {
				fos.write(content);
				fos.flush();
			}

		} catch (FileNotFoundException e) {
			message = "No File found in path: " + fileFullPath;
			System.out.println(e);
		} catch (IOException e) {
			message = "No File found in path: " + fileFullPath;
			System.out.println(e);
		}
		return message;
	}
}
