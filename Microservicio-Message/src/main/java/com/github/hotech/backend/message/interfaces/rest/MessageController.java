package com.github.hotech.backend.message.interfaces.rest;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.domain.model.commands.DeleteMessageCommand;
import com.github.hotech.backend.message.domain.model.queries.GetAllMessagesQuery;
import com.github.hotech.backend.message.domain.model.queries.GetMessageByIdQuery;
import com.github.hotech.backend.message.domain.model.queries.GetMessagesByNotificationIdAndUserAccountIdQuery;
import com.github.hotech.backend.message.domain.services.MessageCommandService;
import com.github.hotech.backend.message.domain.services.MessageQueryService;
import com.github.hotech.backend.message.interfaces.rest.resources.CreateMessageResource;
import com.github.hotech.backend.message.interfaces.rest.resources.MessageResource;
import com.github.hotech.backend.message.interfaces.rest.resources.UpdateMessageResource;
import com.github.hotech.backend.message.interfaces.rest.transform.CreateMessageCommandFromResourceAssembler;
import com.github.hotech.backend.message.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import com.github.hotech.backend.message.interfaces.rest.transform.UpdateMessageCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/messages", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Messages", description = "Messages Management Endpoints")
public class MessageController {

    private final MessageQueryService messageQueryService;
    private final MessageCommandService messageCommandService;

    public MessageController(MessageQueryService messageQueryService, MessageCommandService messageCommandService) {
        this.messageQueryService = messageQueryService;
        this.messageCommandService = messageCommandService;
    }

    @PostMapping
    public ResponseEntity<MessageResource> createMessage(
            @RequestBody CreateMessageResource createMessageResource) {
        Optional<Message> MessageSource = messageCommandService
                .handle(CreateMessageCommandFromResourceAssembler.toCommandFromResource(createMessageResource));
        return MessageSource.map(source ->
                            new ResponseEntity<>(MessageResourceFromEntityAssembler
                                    .toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<MessageResource>> getAllMessages() {
        List<Message> messageSource = messageQueryService.handle(new GetAllMessagesQuery());
        var messageResource = messageSource
                .stream().map(MessageResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(messageResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResource> getMessageByI(@PathVariable Long id) {
        var query = new GetMessageByIdQuery(id);
        var message = messageQueryService.handle(query);
        if (message.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return message.map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                    .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    private ResponseEntity<List<MessageResource>> getMessagesByNotificationIdAndUserAccountIdQuery(Long notificationId, Long userAccountId) {
        var query = new GetMessagesByNotificationIdAndUserAccountIdQuery(notificationId, userAccountId);
        var messages = messageQueryService.handle(query);
        return ResponseEntity.ok(messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<MessageResource> updateMessage(@PathVariable Long messageId, @RequestBody UpdateMessageResource updateMessageResource) {
        var UpdateMessageCommand = UpdateMessageCommandFromResourceAssembler.toCommandFromResource(messageId, updateMessageResource);
        var updatedMessage = messageCommandService.handle(UpdateMessageCommand);
        if (updatedMessage.isEmpty()) return ResponseEntity.notFound().build();

        var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(updatedMessage.get());
        return ResponseEntity.ok(messageResource);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId) {
        var deleteMessagesCommand = new DeleteMessageCommand(messageId);
        messageCommandService.handle(deleteMessagesCommand);
        return ResponseEntity.ok("Message with id" + messageId + " has been deleted.");
    }
}
