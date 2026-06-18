import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  allowedDevOrigins: ["10.68.220.252", "localhost:3000", "10.68.220.252:3000"],
  output: "export",
  basePath: process.env.NODE_ENV === "production" ? "/SKILLSYNCAI_PDD_SPIC7A26" : "",
  images: {
    unoptimized: true,
  },
};

export default nextConfig;
