//
//  StateFlowValue.swift
//  iosApp
//
//  Created by Серега on 28.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class State<T : AnyObject> : ObservableObject {
    
    @Published
    var value: T
    
    var cancellable: Cancellable?
    
    init(_ stateFlow: CStateFlow<T>) {
        self.value = stateFlow.value
        
        cancellable = FlowWrapper<T>(flow: stateFlow).collect(
            consumer: { value in
                if let value = value {
                    self.value = value
                }
            }
        )
    }
    
    deinit {
        self.cancellable?.cancel()
    }
    
}
