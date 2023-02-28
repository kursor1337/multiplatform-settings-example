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
    var messageState: State<NSString>
    @ObservedObject
    var textState: State<NSString>
    
    
    init() {
        messageState = State(componentHolder.component.messageState)
        textState = State(componentHolder.component.textFromSettingsState)
    }

	var body: some View {
        let text = textState.value as String
        Text(text)
        TextField(
            "",
            text: Binding(
                get: {
                    messageState.value as String
                },
                set: { message in
                    componentHolder.component.onMessageChanged(text: message as String)
                }
            )
        )
        Button("Save to regular settings") {
            componentHolder.component.onSaveToRegularClick()
        }
        Button("Save to encrypted settings") {
            componentHolder.component.onSaveToEncryptedClick()
        }
        Button("Get from regular settings") {
            componentHolder.component.onGetFromRegularClick()
        }
        Button("Get from encrypted settings") {
            componentHolder.component.onGetFromEncryptedClick()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
