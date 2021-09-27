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
                content = {
                        @Content(schema = @Schema(implementation = ResponseBody.class),
                                examples = {
                                        @ExampleObject(value =
                                                "{" +
                                                        "\n\t\"timestamp\":1632665596284," +
                                                        "\n\t\"statusCode\":200,\n\t\"status\":\"OK\",\n\t\"message\":[" +
                                                        "\n\t\t{" +
                                                        "\n\t\t\t\"taskId\":26,\n\t\t\"number\":2," +
                                                        "\n\t\t\t\"createDate\":\"2021-09-26T07:56:43.789+00:00\"," +
                                                        "\n\t\t\t\"updateDate\":null," +
                                                        "\n\t\t\t\"status\":\"NOT_COMPLETED\"," +
                                                        "\n\t\t\t\"comment\":\"\"" +
                                                        "\n\t\t}," +
                                                        "\n\t\t{" +
                                                        "\n\t\t\t\"taskId\":27," +
                                                        "\n\t\t\t\"number\":3," +
                                                        "\n\t\t\t\"createDate\":\"2021-09-26T07:56:43.792+00:00\"" +
                                                        "\n\t\t\t,\"updateDate\":null," +
                                                        "\n\t\t\t\"status\":\"NOT_COMPLETED\"," +
                                                        "\n\t\t\t\"comment\":\"\"" +
                                                        "\n\t\t}" +
                                                        "\n\t]" +
                                                        "\n}")
                                }
                        )
                }
        ),
        @ApiResponse(responseCode = "400",
                description = "The start and end dates cannot be empty.",
                content = {
                        @Content(schema = @Schema(implementation = ResponseBody.class),
                                examples = {
                                        @ExampleObject(value =
                                                "{" +
                                                        "\n\t\"timestamp\":\"2021-09-26T14:20:17.599+00:00\"," +
                                                        "\n\t\"statusCode\":400," +
                                                        "\n\t\"status\":\"Bad Request\"," +
                                                        "\n\t\"message\":\"The start and end dates cannot be empty\"" +
                                                        "\n}")
                                }
                        )
                }
        )
})
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskGetApiResponses {
}
