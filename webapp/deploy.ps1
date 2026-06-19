# Build the Next.js application and run path flattener
Write-Host "Building Next.js application..."
npm run build:static

if ($LastExitCode -eq 0) {
    Write-Host "Build succeeded. Deploying to GitHub Pages..."
    
    # Define temporary deployment path
    $TempPath = if ($PSToolspaceTemp) { $PSToolspaceTemp } else { $env:TEMP }
    $TempDeployDir = Join-Path $TempPath "skillsync-deploy"
    if (Test-Path $TempDeployDir) {
        Remove-Item -Recurse -Force $TempDeployDir
    }
    New-Item -ItemType Directory -Path $TempDeployDir | Out-Null
    
    # Copy files from Next.js static output (out) to temp folder
    Copy-Item -Path "out\*" -Destination $TempDeployDir -Recurse -Force
    
    # Switch directory and initialize Git
    Push-Location $TempDeployDir
    git init | Out-Null
    
    # Retrieve dynamic remote URL (including authentication tokens) from main repo
    $RemoteUrl = git -C $PSScriptRoot remote get-url origin
    git remote add origin $RemoteUrl
    git checkout -b gh-pages | Out-Null
    
    # Configure local git user if not present (to ensure commit succeeds in non-interactive environment)
    git config user.name "YLakshmiReddy"
    git config user.email "yarrapureddy666@gmail.com"
    
    # Add files and commit
    git add -A
    git commit -m "Deploy Next.js build to GitHub Pages" | Out-Null
    
    # Force push to gh-pages branch
    Write-Host "Pushing build to origin gh-pages..."
    git push origin gh-pages --force
    
    Pop-Location
    
    # Clean up temp files
    Remove-Item -Recurse -Force $TempDeployDir
    Write-Host "Successfully deployed webapp to GitHub Pages!"
} else {
    Write-Host "Build failed. Aborting deployment."
    exit 1
}
