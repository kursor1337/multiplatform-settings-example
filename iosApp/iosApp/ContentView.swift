import SwiftUI
import shared


struct ContentView: View {
    
    
    private let componentHolder: ComponentHolder = ComponentHolder {
            IosComponentFactory
                .shared
                .createMessageComponent(
                    componentContext: $0
                )
        }
    
    @ObservedObject
    var messageState: State<NSString>
    @ObservedObject
    var textState: State<NSString>
    
    
    init() {
        messageState = State(componentHolder.component.messageState)
        textState = State(componentHolder.component.textFromSettingsState)
    }

	var body: some View {
        Text(textState.value as String)
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
            print(textState.value as String)
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
