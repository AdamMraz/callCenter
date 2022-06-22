package ru.adamDev.callCenter.service.annotation.ApiResponses;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.adamDev.callCenter.service.response.ResponseBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "The update was completed successfully",
                content = {
                        @Content(schema = @Schema(implementation = ResponseBody.class),
                                examples = {
                                        @ExampleObject(value =
                                                "{" +
                                                        "\n\t\"timestamp\":1632666526827," +
                                                        "\n\t\"statusCode\":200," +
                                                        "\n\t\"status\":\"OK\"," +
                                                        "\n\t\"message\":\"Success\"" +
                                                        "\n}")
                                }
                        )
                }
        ),
        @ApiResponse(responseCode = "400",
                content = {
                        @Content(schema = @Schema(implementation = ResponseBody.class),
                                examples = {
                                        @ExampleObject(name = "Illegal number",
                                                description = "The task number cannot be a negative number or 0.",
                                                value =
                                                        "{\n\t" +
                                                                "\"timestamp\":1632662317699," +
                                                                "\n\t\"statusCode\":400," +
                                                                "\n\t\"status\":\"Bad Request\"," +
                                                                "\n\t\"message\":\"The task number cannot be a negative number or 0\"" +
                                                                "\n}"),
                                        @ExampleObject(name = "Task not found",
                                                description = "The task with current id not fount.",
                                                value =
                                                        "{" +
                                                                "\n\t\"timestamp\":1632666723009," +
                                                                "\n\t\"statusCode\":400," +
                                                                "\n\t\"status\":\"Bad Request\"," +
                                                                "\n\t\"message\":\"The task with current id not fount\"" +
                                                                "\n}")
                                }
                        )
                }
        )
})
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskUpdateApiResponses {
}
