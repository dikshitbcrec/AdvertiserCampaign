package com.adtech.controller;




import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adtech.exception.AdNotFoundException;
import com.adtech.model.File;
import com.adtech.service.AdService;
import com.adtech.service.FileService;

@RestController
@RequestMapping("/media")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@Autowired
	private AdService adService;
	
	
	@PostMapping()
    public ResponseEntity<?> create(@RequestBody @RequestParam int adId, @RequestParam("file")MultipartFile file) throws IOException
    {
        String uploadImage=fileService.uploadfile(adId, file);
        return ResponseEntity.ok().body(uploadImage);
                
    }
	
	
	@PutMapping("/{adId}")
	 public ResponseEntity<?> updateFile(@RequestBody @RequestParam("file") MultipartFile file,@PathVariable("adId") int adId) throws IOException
    {
        String updatefil=fileService.UpdateFile(adId, file);
        return ResponseEntity.ok().body(updatefil);
                
    }
	
	
	@GetMapping("/{adId}")
	public ResponseEntity<?> downloadfile(@PathVariable("adId") int adId){
		
		if(adService.getAd(adId)!=null)
		{
			
			Optional<File> getFile=fileService.getById(adId);
			File file=getFile.get();
			
			
			byte[] fileData=fileService.downloadFile(getFile.get().getName());
			if(file.getType().contains("video/mp4"))
			{
				return ResponseEntity.status(HttpStatus.OK)
						.contentType(MediaType.valueOf("video/mp4"))
						.body(fileData);
			}
			else if(file.getType().contains("image/png"))
			{
				return ResponseEntity.status(HttpStatus.OK)
						.contentType(MediaType.valueOf("image/png"))
						.body(fileData);

			}
			else {
				return ResponseEntity.status(HttpStatus.OK)
						.contentType(MediaType.valueOf("audio/wave"))
						.body(fileData);
			}
			
		}
		else
		{
			throw new AdNotFoundException("This Ad Id don't exist");
		}
		
	
		
		
		
	}
	
	
//	
//	@GetMapping("/video-file/{fileName}")
//	public ResponseEntity<?> downloadVideo(@PathVariable String fileName){
//		byte[] videoData=fileService.downloadFile(fileName);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("video/mp4"))
//				.body(videoData);
//
//	}
//	
//	@GetMapping("/audio-file/{fileName}")
//	public ResponseEntity<?> downloadAudio(@PathVariable String fileName){
//		byte[] audioData=fileService.downloadFile(fileName);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("audio/wave"))
//				.body(audioData);
//
//	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteImage(@PathVariable("id") int id){
		
		return new ResponseEntity<String>(fileService.deleteFile(id),HttpStatus.OK);
	}
	
	
	

}
