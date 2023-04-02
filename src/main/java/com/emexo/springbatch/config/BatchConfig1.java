package com.emexo.springbatch.config;

import com.emexo.springbatch.tasks.MyTaskFour;
import com.emexo.springbatch.tasks.MyTaskOne;
import com.emexo.springbatch.tasks.MyTaskThree;
import com.emexo.springbatch.tasks.MyTaskTwo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig1 {

    // predefined class
    @Autowired
    private JobBuilderFactory jobs;

    // predefined class
    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step stepThree(){
        return steps.get("stepThree")
                .tasklet(new MyTaskThree())
                .build();
    }

    @Bean
    public Step stepFour(){
        return steps.get("stepFour")
                .tasklet(new MyTaskFour())
                .build();
    }

    @Bean
    public Job fastJob(){
        return jobs.get("fastJob")
                // job id
                .incrementer(new RunIdIncrementer())
                .start(stepThree())
                .next(stepFour())
                .build();
    }
}
