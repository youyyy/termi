package com.ue.termi.rpc;/*
          .--,       .--,
         ( (  \.---./  ) )
          '.__/o   o\__.'
             {=  ^  =}
              >  -  <
             /       \
            //       \\
           //|   .   |\\
           "'\       /'"_.-~^`'-.
              \  _  /--'         `
            ___)( )(___
           (((__) (__)))
   高山仰止,景行行止.虽不能至,心向往之。
*/

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.util.*;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-04-04 20:18
 **/
@Slf4j
public class ChatGptApi {
    private static final String token = "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";

    private static final OpenAiService openAiService = new OpenAiService(token);

    public static LinkedHashMap<String, List<ChatMessage>> chatMap = new LinkedHashMap<>();

    public static String chatModel = "gpt-3.5-turbo";
    public static String roleSystem = "system";
    public static String roleUser = "user";
    public static String roleAssistant = "assistant";

    /**
     * 一轮对话的完成
     * 以start为开始标志--true开始，以finish为结束标志--true结束，以chat为用户发送的聊天内容
     *
     * @return 返回聊天回答和令牌消耗
     */
    public static String getBatchChat(String user, String chat) {
        if (Strings.isBlank(user)) {
            return null;
        }
        if (chatMap.size() >= 10) {
            log.info("clean 缓存");
            chatMap.clear();
        }
        List<ChatMessage> chatMessages = chatMap.getOrDefault(user, new ArrayList<>());
        if (chatMessages.size() > 10) {
            log.info("remove 0");
            chatMessages.remove(0);
        }
        //获取chatGPT的聊天结果，将结果添加到chat列表中
        return getOneChat(chat, roleUser, user, chatMessages);
    }

    /**
     * 在一轮对话中，不断记录之前的聊天列表，并按照身份记录其发言，将chat列表传递给模型以实现聊天
     */
    public static String getOneChat(String newChat, String role, String user, List<ChatMessage> chatMessages) {
        if (null == chatMessages){
            chatMessages = new ArrayList<>();
        }
        ChatMessage newMessage = new ChatMessage();
        newMessage.setRole(role);
        newMessage.setContent(newChat);
        chatMessages.add(newMessage);
        // 如果是助手角色，也就是chatGPT的回答结果，则不用发送request，只是用于存入chatList，不做其他工作
        if (roleAssistant.equals(role)) {
            return null;
        }

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(chatModel)
                .messages(chatMessages)
                .build();
        ChatCompletionResult result = openAiService.createChatCompletion(chatCompletionRequest);
        ChatMessage message = result.getChoices().get(0).getMessage();
        chatMessages.add(message);
        chatMap.put(user, chatMessages);
        return message.getContent();
    }

}
