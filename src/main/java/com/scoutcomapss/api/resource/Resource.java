package com.scoutcomapss.api.resource;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/19/2024
 */


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "_resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    private String resourceName;
    private String resourceType;
    private String resourceFilePath;
}

