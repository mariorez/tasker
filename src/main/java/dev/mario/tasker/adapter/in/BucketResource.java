package dev.mario.tasker.adapter.in;

import dev.mario.tasker.core.usecase.CreateBucket;
import dev.mario.tasker.core.usecase.CreateBucketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/buckets")
public class BucketResource {

    @Autowired
    private CreateBucketHandler createBucketHandler;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateBucket createBucket) {
        createBucketHandler.handle(createBucket);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
