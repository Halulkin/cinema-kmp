/// Elegant Koin Injection for Swift in Kotlin Multiplatform Mobile:
/// https://medium.com/@uwaisalqadri/elegant-koin-injection-for-swift-in-kotlin-multiplatform-mobile-9a803f6efb2e

import ComposeApp
import Foundation

/// Typealiases for Koin classes to simplify usage in Swift
typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin

extension KoinApplication {
    /// Starts the Koin application and returns the instance
    static let composeApp = companion.start()

    /// Initializes and starts the Koin application
    @discardableResult
    static func start() -> KoinApplication {
        composeApp
    }
}

extension KoinApplication {
    /// List of key paths for Koin dependencies
    private static let keyPaths: [PartialKeyPath<Koin>] = []

    /// Injects a dependency of type T from the Koin application
    static func inject<T>() -> T {
        composeApp.inject()
    }

    /// Injects a dependency of type T from the Koin instance
    func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }
        fatalError("\(T.self) is not registered with KoinApplication")
    }
}

/// Property wrapper for lazy Koin dependency injection
@propertyWrapper
struct LazyKoin<T> {
    lazy var wrappedValue: T = { KoinApplication.composeApp.inject() }()

    init() { }
}
