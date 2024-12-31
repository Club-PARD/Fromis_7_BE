package com.example.fromis_7_be.share.service;

import com.example.fromis_7_be.share.entity.Share;
import com.example.fromis_7_be.share.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final ShareRepository shareRepository;

}
