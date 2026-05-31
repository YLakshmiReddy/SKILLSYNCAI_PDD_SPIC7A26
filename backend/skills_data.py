SKILL_MAP = {
    "AI Agents": [
        "langchain", "autogen", "crewai", "babyagi", "agentic", "tool use", 
        "function calling", "autonomous agents", "multi-agent systems", "semantic kernel"
    ],
    "AI Red Teaming": [
        "adversarial attacks", "prompt injection", "jailbreaking", "llm security", 
        "red teaming", "model robustness", "owasp llm", "safety alignment", "bias detection"
    ],
    "API Design": [
        "rest", "graphql", "grpc", "openapi", "swagger", "postman", 
        "endpoints", "webhooks", "rate limiting", "payload", "json", "authentication"
    ],
    "ASP.NET Core": [
        "c#", ".net core", "entity framework", "razor pages", "blazor", 
        "linq", "nuget", "iis", "model-view-controller", "mvc", "web api"
    ],
    "AWS": [
        "amazon web services", "ec2", "s3", "lambda", "rds", "dynamodb", 
        "iam", "vpc", "route 53", "cloudformation", "amplify", "ebs"
    ],
    "Angular": [
        "typescript", "rxjs", "directives", "angular cli", "ngmodules", 
        "components", "data binding", "zone.js", "angular material", "spa"
    ],
    "Azure": [
        "microsoft azure", "azure functions", "cosmos db", "active directory", 
        "blob storage", "app service", "azure devops", "virtual machines", "aks"
    ],
    "C++": [
        "cpp", "stl", "pointers", "multithreading", "memory management", 
        "template metaprogramming", "oops", "data structures", "algorithms", "cmake"
    ],
    "CSS": [
        "flexbox", "css grid", "sass", "less", "tailwind", "responsive design", 
        "media queries", "animations", "transitions", "specificity", "box model"
    ],
    "Cloudflare": [
        "cloudflare workers", "dns", "cdn", "waf", "ddos protection", 
        "zero trust", "pages", "r2 storage", "kv storage", "load balancing"
    ],
    "Code Review": [
        "pull requests", "pr review", "static analysis", "linting", "code quality", 
        "refactoring", "maintainability", "best practices", "peer review", "dry principle"
    ],
    "Computer Science": [
        "operating systems", "computer architecture", "networking", "compilers", 
        "automata theory", "discrete mathematics", "binary", "assembly", "computation"
    ],
    "Data Structures and Algorithms": [
        "dsa", "linked list", "binary search tree", "hash map", "sorting algorithms", 
        "big o notation", "dynamic programming", "recursion", "graphs", "stacks", "queues"
    ],
    "Design System": [
        "atomic design", "component library", "style guide", "design tokens", 
        "consistency", "reusability", "typography", "color palette", "accessibility", "a11y"
    ],
    "Docker": [
        "containers", "dockerfile", "docker-compose", "images", "volumes", 
        "containerization", "registry", "orchestration", "networking", "port mapping"
    ],
    "Flutter": [
        "dart", "widgets", "state management", "riverpod", "bloc", "provider", 
        "hot reload", "material design", "cupertino", "cross-platform", "native bridge"
    ],
    "GCP": [
        "google cloud platform", "compute engine", "app engine", "bigquery", 
        "cloud functions", "kubernetes engine", "gke", "cloud storage", "pub/sub", "cloud sql"
    ],
    "Git": [
        "version control", "branching", "merging", "rebasing", "commits", 
        "repository", "stash", "cherry-pick", "merge conflicts", "gitflow"
    ],
    "Go": [
        "golang", "goroutines", "channels", "interfaces", "concurrency", 
        "static typing", "garbage collection", "go modules", "structs", "pointers"
    ],
    "GraphQL": [
        "queries", "mutations", "subscriptions", "schema", "resolvers", 
        "apollo", "introspection", "typed schema", "fragments", "graphiql"
    ],
    "HTML": [
        "html5", "semantic tags", "dom", "forms", "attributes", 
        "canvas", "svg", "web storage", "metadata", "hyperlinks"
    ],
    "Java": [
        "oops", "jvm", "jdk", "spring", "spring boot", "maven", 
        "multithreading", "garbage collection", "hibernate", "microservices"
    ],
    "JavaScript": [
        "es6+", "closures", "promises", "async/await", "dom manipulation", 
        "event loop", "callback", "prototypes", "vanilla js", "json"
    ],
    "Kotlin": [
        "null safety", "coroutines", "jetpack compose", "android", 
        "interoperability", "extension functions", "data classes", "kmm"
    ],
    "Kubernetes": [
        "k8s", "pods", "nodes", "clusters", "helm", "kubectl", 
        "deployment", "service mesh", "ingress", "autoscaling"
    ],
    "Linux": [
        "bash", "shell scripting", "ubuntu", "debian", "centos", 
        "permissions", "cron jobs", "kernel", "ssh", "terminal"
    ],
    "MongoDB": [
        "nosql", "documents", "collections", "aggregation", "mongoose", 
        "bson", "sharding", "indexing", "atlas", "json-like"
    ],
    "Next.js": [
        "ssr", "ssg", "server components", "app router", "api routes", 
        "hydration", "vercel", "middleware", "static generation", "isr"
    ],
    "NoSQL": [
        "document store", "key-value", "wide-column", "graph database", 
        "scalability", "schema-less", "cap theorem", "eventual consistency"
    ],
    "Node.js": [
        "npm", "express", "event-driven", "non-blocking", "v8 engine", 
        "streams", "buffer", "middleware", "package.json", "backend"
    ],
    "PHP": [
        "laravel", "symfony", "codeigniter", "wordpress", "composer", 
        "xampp", "lamp stack", "object-oriented php", "pdo", "mysqli"
    ],
    "Prompt Engineering": [
        "few-shot prompting", "chain of thought", "system instructions", "llm optimization", 
        "temperature", "top-p", "tokenization", "context window", "iterative refining", "hallucination mitigation"
    ],
    "Python": [
        "django", "flask", "fastapi", "pip", "virtualenv", 
        "pandas", "numpy", "list comprehensions", "generators", "decorators"
    ],
    "React": [
        "hooks", "redux", "context api", "jsx", "virtual dom", 
        "components", "props", "state management", "react router", "axios"
    ],
    "React Native": [
        "mobile development", "expo", "native modules", "hermes", "reanimated", 
        "cross-platform", "bridge", "flexbox", "view", "text"
    ],
    "Redis": [
        "caching", "in-memory database", "pub/sub", "data persistence", "key-value store", 
        "streams", "sentinel", "clustering", "sorted sets", "eviction policy"
    ],
    "Rust": [
        "ownership", "borrow checker", "cargo", "crates", "traits", 
        "memory safety", "zero-cost abstractions", "pattern matching", "structs", "enums"
    ],
    "SQL": [
        "mysql", "postgresql", "queries", "joins", "indexing", 
        "acid properties", "stored procedures", "views", "normalization", "triggers"
    ],
    "Software Design and Architecture": [
        "solid principles", "design patterns", "microservices", "monolithic", "clean architecture", 
        "system design", "scalability", "load balancing", "uml", "ddd"
    ],
    "Spring Boot": [
        "dependency injection", "ioc container", "spring security", "spring data jpa", "rest controller", 
        "annotations", "autoconfiguration", "beans", "actuator", "thymeleaf"
    ],
    "System Design": [
        "scalability", "availability", "load balancing", "caching", "sharding", 
        "microservices", "proxies", "cdn", "database replication", "cap theorem", 
        "distributed systems", "message queues", "latency", "throughput"
    ],
    "Terraform": [
        "infrastructure as code", "iac", "hcl", "tfstate", "plan", "apply", 
        "providers", "modules", "workspace", "resource provisioning", "terraform cloud"
    ],
    "TypeScript": [
        "static typing", "interfaces", "generics", "enums", "type inference", 
        "union types", "tsconfig", "decorator", "structural typing", "any", "unknown"
    ],
    "Vue.js": [
        "vuex", "composition api", "directives", "vue-router", "nuxt.js", 
        "single file components", "v-bind", "v-model", "virtual dom", "pinia"
    ],
    "Frontend": [
        "client-side", "responsive web design", "browser compatibility", "dom", 
        "web performance", "accessibility", "a11y", "ui components", "single page application", 
        "spa", "front-end architecture", "ux implementation"
    ],
    "Backend": [
        "server-side", "database management", "api development", "authentication", 
        "server architecture", "data persistence", "multithreading", "caching", 
        "concurrency", "orm", "session management", "load balancing"
    ],
    "Full Stack": [
        "mern stack", "mean stack", "lamp stack", "end-to-end development", 
        "client-server architecture", "database integration", "restful services", 
        "middleware", "version control", "deployment", "full lifecycle"
    ],
    "AI Engineer": [
        "machine learning", "deep learning", "neural networks", "nlp", "computer vision", 
        "model deployment", "data preprocessing", "pytorch", "tensorflow", "fine-tuning", 
        "raging", "vector databases", "llmops"
    ],
    "DevOps": [
        "ci/cd", "infrastructure as code", "automation", "monitoring", "site reliability", 
        "sre", "logging", "orchestration", "pipelines", "containerization", 
        "cloud infrastructure", "security compliance"
    ],
    "UX Design": [
        "user-centered design", "information architecture", "usability testing", 
        "user personas", "customer journey", "wireframing", "high-fidelity prototypes", 
        "empathy mapping", "accessibility standards", "interaction design"
    ],
    "Cyber Security": [
        "penetration testing", "vulnerability assessment", "incident response", 
        "cryptography", "network security", "siem", "firewalls", "threat hunting", 
        "soc", "ethical hacking", "forensics", "compliance"
    ],
    "Game Developer": [
        "unity", "unreal engine", "c#", "c++", "game physics", "shaders", 
        "level design", "3d modeling", "animation", "game loop", "optimization", 
        "multiplayer", "rendering"
    ],
    "Server Side Game Dev": [
        "photon", "mirror", "backend for frontend", "bff", "socket.io", 
        "authoritative server", "latency compensation", "matchmaking", 
        "real-time synchronization", "distributed game servers"
    ],
    "MLOps": [
        "model tracking", "data lineage", "mlflow", "kubeflow", "dvc", 
        "model versioning", "deployment pipelines", "feature stores", 
        "monitoring drift", "automated retraining"
    ],
    "Product Manager": [
        "agile", "scrum", "product roadmap", "user stories", "stakeholder management", 
        "market research", "kpis", "minimum viable product", "mvp", "backlog prioritization", 
        "customer discovery"
    ],
    "DevSecOps": [
        "sast", "dast", "security as code", "vulnerability scanning", 
        "compliance automation", "secure ci/cd", "threat modeling", 
        "secrets management", "security audits"
    ],
    "Data Analysis": [
        "exploratory data analysis", "eda", "data cleaning", "statistical modeling", 
        "visualization", "tableau", "power bi", "sql queries", "trend analysis", 
        "reporting", "excel", "hypothesis testing"
    ],
    "BI Analyst": [
        "business intelligence", "data warehousing", "etl processes", "dashboards", 
        "olap cubes", "dimensional modeling", "looker", "microstrategy", 
        "decision support systems", "data governance"
    ],
    "Developer Relations": [
        "developer advocacy", "devrel", "technical writing", "community building", 
        "developer experience", "dx", "public speaking", "hackathons", 
        "api documentation", "developer feedback loops"
    ],
    "Data Science": [
        "predictive modeling", "machine learning", "r programming", "python", 
        "data mining", "feature engineering", "experimental design", 
        "big data", "hadoop", "spark", "mathematical optimization"
    ],
    "Data Engineer": [
        "etl", "data pipeline", "apache spark", "hadoop", "kafka", 
        "data warehousing", "big data", "airflow", "snowflake", "data lake", 
        "batch processing", "stream processing"
    ],
    "Android": [
        "kotlin", "java", "android sdk", "jetpack compose", "retrofit", 
        "mvvm", "room database", "dagger hilt", "android studio", "material design", 
        "xml layout", "apk"
    ],
    "Machine Learning": [
        "supervised learning", "unsupervised learning", "regression", "classification", 
        "scikit-learn", "pandas", "numpy", "model evaluation", "cross-validation", 
        "hyperparameter tuning", "feature selection"
    ],
    "PostgreSQL": [
        "relational database", "psql", "stored procedures", "pgadmin", "acid", 
        "query optimization", "jsonb", "indexing", "triggers", "foreign keys"
    ],
    "iOS": [
        "swift", "objective-c", "xcode", "uikit", "swiftui", "cocoapods", 
        "core data", "testflight", "apple developer", "ios architecture"
    ],
    "Blockchain": [
        "smart contracts", "solidity", "ethereum", "web3", "dapps", 
        "cryptography", "decentralized", "hyperledger", "consensus algorithms", 
        "truffle", "hardhat"
    ],
    "QA": [
        "manual testing", "automation testing", "selenium", "cypress", "unit testing", 
        "integration testing", "bug reporting", "test cases", "quality assurance", 
        "regression testing", "jmeter"
    ],
    "Software Architecture": [
        "microservices", "monolith", "serverless", "event-driven", "layered architecture", 
        "clean code", "system design", "scalability", "high availability", "design patterns"
    ],
    "Technical Writer": [
        "documentation", "api docs", "markdown", "dita", "instructional design", 
        "release notes", "white papers", "user guides", "confluence", "gitbook"
    ]
}