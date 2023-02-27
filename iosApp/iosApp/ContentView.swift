import SwiftUI
import shared

class Content : ObservableObject {
    @Published var messageState: String = ""
    @Published var textState: String = ""
}

struct ContentView: View {
    
    private let componentHolder: ComponentHolder = ComponentHolder {
            IosComponentFactory
                .shared
                .createMessageComponent(
                    componentContext: $0
                )
        }
	let greet = "Greeting().greet()"
    
    @ObservedObject
    var content = Content()
    
    init() {
        StateFlowUtilsKt
            .wrap(componentHolder.component.messageState)
            .watch { (it: AnyObject?) in
                let message = it as! String
                content.messageState = message
                
        }
        StateFlowUtilsKt
            .wrap(componentHolder.component.textFromSettingsState)
            .watch { (it: AnyObject?) in
                let text = it as! String
                content.textState = text
            }
    }

	var body: some View {
        Text(content.messageState)
        TextField(title: StringProtocol(""), text: $content.messageState)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
