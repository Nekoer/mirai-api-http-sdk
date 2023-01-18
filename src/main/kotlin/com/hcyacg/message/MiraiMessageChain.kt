package com.hcyacg.message

import com.hcyacg.message.event.bot.*
import com.hcyacg.message.event.command.*
import com.hcyacg.message.event.friend.*
import com.hcyacg.message.event.group.*
import com.hcyacg.message.event.other.*
import com.hcyacg.message.messages.*
import com.hcyacg.message.type.*
import com.hcyacg.message.type.Json
import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * @Author Nekoer
 * @Date  1/16/2023 13:51
 * @Description
 **/
@Serializable
open class MiraiMessageChain

object MiraiMessageChainSerializer : JsonContentPolymorphicSerializer<MiraiMessageChain>(MiraiMessageChain::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out MiraiMessageChain> {
        when(element.jsonObject["type"]?.jsonPrimitive?.content){
            "StrangerMessage" -> return StrangerMessage.serializer()
            "StrangerSyncMessage" -> return StrangerSyncMessage.serializer()
            "FriendMessage" -> return FriendMessage.serializer()
            "FriendSyncMessage" -> return FriendSyncMessage.serializer()
            "GroupMessage" -> return GroupMessage.serializer()
            "GroupSyncMessage" -> return GroupSyncMessage.serializer()
            "TempMessage" -> return TempMessage.serializer()
            "TempSyncMessage" -> return TempSyncMessage.serializer()
            "OtherClientMessage" -> return OtherClientMessage.serializer()

            "App" -> return App.serializer()
            "At" -> return At.serializer()
            "AtAll" -> return AtAll.serializer()
            "Dice" -> return Dice.serializer()
            "Face" -> return Face.serializer()
            "File" -> return File.serializer()
            "FlashImage" -> return FlashImage.serializer()
            "ForwardMessage" -> return ForwardMessage.serializer()
            "Image" -> return Image.serializer()
            "Json" -> return Json.serializer()
            "MarketFace" -> return MarketFace.serializer()
            "MiraiCode" -> return MiraiCode.serializer()
            "MusicShare" -> return MusicShare.serializer()
            "Plain" -> return Plain.serializer()
            "Poke" -> return Poke.serializer()
            "Quote" -> return Quote.serializer()
            "Source" -> return Source.serializer()
            "Voice" -> return Voice.serializer()
            "Xml" -> return Xml.serializer()

            "BotOfflineEventActive" -> return BotOfflineEventActive.serializer()
            "BotOfflineEventDropped" -> return BotOfflineEventDropped.serializer()
            "BotOfflineEventForce" -> return BotOfflineEventForce.serializer()
            "BotOnlineEvent" -> return BotOnlineEvent.serializer()
            "BotReloginEvent" -> return BotReloginEvent.serializer()

            "CommandExecutedEvent" -> return CommandExecutedEvent.serializer()

            "FriendInputStatusChangedEvent" -> return FriendInputStatusChangedEvent.serializer()
            "FriendNickChangedEvent" -> return FriendNickChangedEvent.serializer()
            "FriendRecallEvent" -> return FriendRecallEvent.serializer()
            "NewFriendRequestEvent" -> return NewFriendRequestEvent.serializer()

            "OtherClientOfflineEvent" -> return OtherClientOfflineEvent.serializer()
            "OtherClientOnlineEvent" -> return OtherClientOnlineEvent.serializer()

            "BotGroupPermissionChangeEvent" -> return BotGroupPermissionChangeEvent.serializer()
            "BotInvitedJoinGroupRequestEvent" -> return BotInvitedJoinGroupRequestEvent.serializer()
            "BotJoinGroupEvent" -> return BotJoinGroupEvent.serializer()
            "BotLeaveEventActive" -> return BotLeaveEventActive.serializer()
            "BotLeaveEventDisband" -> return BotLeaveEventDisband.serializer()
            "BotLeaveEventKick" -> return BotLeaveEventKick.serializer()
            "BotMuteEvent" -> return BotMuteEvent.serializer()
            "BotUnmuteEvent" -> return BotUnmuteEvent.serializer()
            "GroupAllowAnonymousChatEvent" -> return GroupAllowAnonymousChatEvent.serializer()
            "GroupAllowConfessTalkEvent" -> return GroupAllowConfessTalkEvent.serializer()
            "GroupAllowMemberInviteEvent" -> return GroupAllowMemberInviteEvent.serializer()
            "GroupEntranceAnnouncementChangeEvent" -> return GroupEntranceAnnouncementChangeEvent.serializer()
            "GroupMuteAllEvent" -> return GroupMuteAllEvent.serializer()
            "GroupNameChangeEvent" -> return GroupNameChangeEvent.serializer()
            "GroupRecallEvent" -> return GroupRecallEvent.serializer()
            "MemberCardChangeEvent" -> return MemberCardChangeEvent.serializer()
            "MemberHonorChangeEvent" -> return MemberHonorChangeEvent.serializer()
            "MemberJoinEvent" -> return MemberJoinEvent.serializer()
            "MemberJoinRequestEvent" -> return MemberJoinRequestEvent.serializer()
            "MemberLeaveEventKick" -> return MemberLeaveEventKick.serializer()
            "MemberLeaveEventQuit" -> return MemberLeaveEventQuit.serializer()
            "MemberMuteEvent" -> return MemberMuteEvent.serializer()
            "MemberPermissionChangeEvent" -> return MemberPermissionChangeEvent.serializer()
            "MemberSpecialTitleChangeEvent" -> return MemberSpecialTitleChangeEvent.serializer()
            "MemberUnmuteEvent" -> return MemberUnmuteEvent.serializer()
            "NudgeEvent" -> return NudgeEvent.serializer()

        }
        return MiraiMessageChain.serializer()
    }

}
