import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent


class BaseListener : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        println("The bot is ready")
    }

}
fun main() {
    val botToken = System.getenv("BOT_TOKEN")

    if (botToken == "" || botToken == null) throw Exception("The bot token is undefined")

    val jda = JDABuilder.createDefault(botToken)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
        .build()


    jda.addEventListener(BaseListener())
}