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
    
    private let stateFlow: CStateFlow<T>
    
    @Published
    var value: T

    init(_ stateFlow: CStateFlow<T>) {
        self.stateFlow = stateFlow
        self.value = stateFlow.value
        
        let collector: (T) -> Void = { [weak self] value in self?.value = value }
        self.stateFlow.collect(block: collector)
    }
    
}
