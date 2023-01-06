package com.adtech.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {
    @Id
    @Column(name="ad_id")
    private int adId;
    @Column(name="filename")
    private String name;
    @Column(name="mediatype")
    private String type;
  
    @Lob
    @Column(name = "filedata",length = 1000)
    private byte[] imageData;
}