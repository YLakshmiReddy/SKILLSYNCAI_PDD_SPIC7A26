const fs = require('fs');
const path = require('path');

const outDir = path.join(__dirname, 'out');

function walk(dir) {
  let results = [];
  const list = fs.readdirSync(dir);
  list.forEach(file => {
    const fullPath = path.join(dir, file);
    const stat = fs.statSync(fullPath);
    if (stat && stat.isDirectory()) {
      results = results.concat(walk(fullPath));
    } else {
      results.push(fullPath);
    }
  });
  return results;
}

if (fs.existsSync(outDir)) {
  console.log('Fixing Next.js Windows static export paths in:', outDir);
  const files = walk(outDir);
  let copyCount = 0;
  
  files.forEach(file => {
    const relativePath = path.relative(outDir, file);
    // Split by any path separator (Windows \ or POSIX /)
    const segments = relativePath.split(/[\\/]/);
    
    // Find the first segment starting with "__next."
    const index = segments.findIndex(seg => seg.startsWith('__next.'));
    if (index !== -1) {
      const parentDirSegments = segments.slice(0, index);
      const remainingSegments = segments.slice(index);
      
      const newFileName = remainingSegments.join('.');
      const destDir = path.join(outDir, ...parentDirSegments);
      const destPath = path.join(destDir, newFileName);
      
      if (file !== destPath) {
        fs.copyFileSync(file, destPath);
        console.log(`Copied: ${relativePath} -> ${path.relative(outDir, destPath)}`);
        copyCount++;
      }
    }
  });
  console.log(`Successfully copied ${copyCount} files to match Next.js client-side router prefetch path requests.`);
} else {
  console.error(`Directory not found: ${outDir}`);
}
