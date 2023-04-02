package com.emexo.springbatch.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reportingapi.model.ApplicationVO;


public class MyTaskOne implements Tasklet {
    @Value("${application.url}")
    private String applicationUrl;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        System.out.println("MyTaskOne start..");

        WebClient webClient = WebClient.create(applicationUrl);
        Flux<ApplicationVO> result = webClient.get()
                .retrieve()
                .bodyToFlux(ApplicationVO.class);
      //  return result.collectList().block();

        System.out.println("MyTaskOne done..");
        return RepeatStatus.FINISHED;
    }
}